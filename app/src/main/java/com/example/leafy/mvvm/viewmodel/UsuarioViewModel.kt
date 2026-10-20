package com.example.leafy.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafy.mvvm.model.data.Usuario
import com.example.leafy.mvvm.model.repository.ConsultaResiduoRepository
import com.example.leafy.mvvm.model.repository.SugestaoResiduoRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UsuarioViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private var perfilListener: ListenerRegistration? = null

    private val sugestaoResiduoRepository = SugestaoResiduoRepository()
    private val consultaResiduoRepository = ConsultaResiduoRepository()

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    private val _sugestoesEnviadas = MutableStateFlow(0)
    val sugestoesEnviadas: StateFlow<Int> = _sugestoesEnviadas

    private val _consultasRealizadas = MutableStateFlow(0)
    val consultasRealizadas: StateFlow<Int> = _consultasRealizadas

    private val _carregando = MutableStateFlow(false)
    val carregando: StateFlow<Boolean> = _carregando

    private val _erro = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _erro

    private val _sucesso = MutableStateFlow<String?>(null)
    val sucesso: StateFlow<String?> = _sucesso

    private val _sequenciaDias = MutableStateFlow(0)
    val sequenciaDias: StateFlow<Int> = _sequenciaDias

    companion object {
        private const val XP_USO_DIARIO = 20
    }

    private fun dataAtual(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.getDefault()
        ).format(Date())
    }

    private fun diaAtual(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date())
    }

    private fun nivelPorXp(xp: Int): String {
        return when {
            xp < 100 -> "Formiga Operária"
            xp < 300 -> "Castor Engenheiro"
            xp < 600 -> "Lobo Guardião"
            xp < 1000 -> "Condor dos Andes"
            xp < 1500 -> "Tartaruga Milenar"
            else -> "Fênix"
        }
    }

    private suspend fun carregarPerfil(
        uid: String,
        email: String
    ): Usuario {

        val referencia = firestore
            .collection("usuarios")
            .document(uid)

        var documento = referencia.get().await()

        if (!documento.exists()) {
            val nome = auth.currentUser?.displayName
                ?: email.substringBefore("@")

            val data = dataAtual()

            val dadosUsuario = mapOf(
                "nome" to nome,
                "email" to email,
                "xp" to 0,
                "nivel" to "Formiga Operária",
                "criado_em" to data,
                "tipoUsuario" to "usuario",
                "sequenciaDias" to 0,
                "ultimoAcessoDia" to ""
            )

            referencia.set(dadosUsuario).await()
            documento = referencia.get().await()
        }

        val nome = documento.getString("nome")
            ?: auth.currentUser?.displayName
            ?: email.substringBefore("@")

        val xp = documento.getLong("xp")?.toInt() ?: 0
        val nivel = nivelPorXp(xp)

        val criadoEm = documento.getString("criado_em")
            ?: documento.getString("criadoEm")
            ?: dataAtual()

        val tipoUsuario = documento.getString("tipoUsuario")
            ?: "usuario"

        _sequenciaDias.value =
            documento.getLong("sequenciaDias")?.toInt() ?: 0

        observarPerfil(uid, email)

        return Usuario(
            id = uid.hashCode(),
            nome = nome,
            email = email,
            xp = xp,
            nivel = nivel,
            criadoEm = criadoEm,
            tipoUsuario = tipoUsuario
        )
    }

    private fun observarPerfil(uid: String, email: String) {
        perfilListener?.remove()

        perfilListener = firestore
            .collection("usuarios")
            .document(uid)
            .addSnapshotListener { documento, erro ->

                if (erro != null || documento == null ||
                    !documento.exists()
                ) {
                    return@addSnapshotListener
                }

                val xpAtual =
                    documento.getLong("xp")?.toInt() ?: 0

                val nomeAtual = documento.getString("nome")
                    ?: auth.currentUser?.displayName
                    ?: email.substringBefore("@")

                val nivelAtual = nivelPorXp(xpAtual)

                val criadoEm = documento.getString("criado_em")
                    ?: documento.getString("criadoEm")
                    ?: dataAtual()

                val tipoUsuario =
                    documento.getString("tipoUsuario") ?: "usuario"

                _sequenciaDias.value =
                    documento.getLong("sequenciaDias")?.toInt() ?: 0

                _usuario.value = Usuario(
                    id = uid.hashCode(),
                    nome = nomeAtual,
                    email = documento.getString("email") ?: email,
                    xp = xpAtual,
                    nivel = nivelAtual,
                    criadoEm = criadoEm,
                    tipoUsuario = tipoUsuario
                )
            }
    }

    private suspend fun registrarAcessoDiario(uid: String) {

        val referencia = firestore
            .collection("usuarios")
            .document(uid)

        val hoje = diaAtual()

        var novoXp = 0
        var novoNivel = ""
        var novaSequencia = 0
        var ganhouXp = false

        firestore.runTransaction { transacao ->

            val documento = transacao.get(referencia)

            check(documento.exists()) {
                "Perfil do usuário não encontrado."
            }

            val tipoUsuario =
                documento.getString("tipoUsuario") ?: "usuario"

            if (tipoUsuario != "usuario") {
                novaSequencia =
                    documento.getLong("sequenciaDias")?.toInt() ?: 0

                novoXp = documento.getLong("xp")?.toInt() ?: 0
                novoNivel = nivelPorXp(novoXp)

                return@runTransaction null
            }

            val ultimoAcesso =
                documento.getString("ultimoAcessoDia") ?: ""

            val sequenciaAtual =
                documento.getLong("sequenciaDias")?.toInt() ?: 0

            val xpAtual =
                documento.getLong("xp")?.toInt() ?: 0

            if (ultimoAcesso == hoje) {
                novaSequencia = sequenciaAtual
                novoXp = xpAtual
                novoNivel = nivelPorXp(xpAtual)

                return@runTransaction null
            }

            val formato = SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
            )

            val dataHoje = formato.parse(hoje)

            val dataUltimoAcesso = if (ultimoAcesso.isNotBlank()) {
                try {
                    formato.parse(ultimoAcesso)
                } catch (_: Exception) {
                    null
                }
            } else {
                null
            }

            val diferencaDias =
                if (dataHoje != null && dataUltimoAcesso != null) {
                    (dataHoje.time - dataUltimoAcesso.time) /
                            (1000L * 60 * 60 * 24)
                } else {
                    -1L
                }

            novaSequencia = if (diferencaDias == 1L) {
                sequenciaAtual + 1
            } else {
                1
            }

            novoXp = xpAtual + XP_USO_DIARIO
            novoNivel = nivelPorXp(novoXp)
            ganhouXp = true

            transacao.update(
                referencia,
                mapOf(
                    "xp" to novoXp,
                    "nivel" to novoNivel,
                    "sequenciaDias" to novaSequencia,
                    "ultimoAcessoDia" to hoje
                )
            )

            null
        }.await()

        _sequenciaDias.value = novaSequencia

        if (ganhouXp) {
            _usuario.value = _usuario.value?.copy(
                xp = novoXp,
                nivel = novoNivel
            )
        }
    }

    fun cadastrar(
        nome: String,
        email: String,
        senha: String
    ) {
        viewModelScope.launch {
            _carregando.value = true
            _erro.value = null
            _sucesso.value = null

            try {
                val resultado = auth
                    .createUserWithEmailAndPassword(
                        email.trim(),
                        senha
                    )
                    .await()

                val usuarioFirebase = resultado.user
                    ?: throw Exception(
                        "Não foi possível criar a conta."
                    )

                val uid = usuarioFirebase.uid
                val nomeLimpo = nome.trim()
                val emailLimpo = email.trim()
                val data = dataAtual()

                firestore.collection("usuarios")
                    .document(uid)
                    .set(
                        mapOf(
                            "nome" to nomeLimpo,
                            "email" to emailLimpo,
                            "xp" to 0,
                            "nivel" to "Formiga Operária",
                            "criado_em" to data,
                            "tipoUsuario" to "usuario",
                            "sequenciaDias" to 0,
                            "ultimoAcessoDia" to ""
                        )
                    )
                    .await()

                _usuario.value = Usuario(
                    id = uid.hashCode(),
                    nome = nomeLimpo,
                    email = emailLimpo,
                    xp = 0,
                    nivel = "Formiga Operária",
                    criadoEm = data,
                    tipoUsuario = "usuario"
                )

                observarPerfil(uid, emailLimpo)
                registrarAcessoDiario(uid)
                carregarQuantidadeSugestoes()
                carregarQuantidadeConsultas()

                _sucesso.value = "Cadastro realizado com sucesso"

            } catch (e: Exception) {
                _erro.value = when (
                    (e as? FirebaseAuthException)?.errorCode
                ) {
                    "ERROR_EMAIL_ALREADY_IN_USE" ->
                        "Este e-mail já possui uma conta."

                    "ERROR_INVALID_EMAIL" ->
                        "Digite um e-mail válido."

                    "ERROR_WEAK_PASSWORD" ->
                        "A senha precisa ter pelo menos 6 caracteres."

                    else ->
                        "Não foi possível realizar o cadastro. Verifique sua conexão e tente novamente."
                }
            } finally {
                _carregando.value = false
            }
        }
    }

    fun login(
        email: String,
        senha: String
    ) {
        viewModelScope.launch {
            _carregando.value = true
            _erro.value = null
            _sucesso.value = null

            try {
                val resultado = auth
                    .signInWithEmailAndPassword(
                        email.trim(),
                        senha
                    )
                    .await()

                val usuarioFirebase = resultado.user
                    ?: throw Exception(
                        "Não foi possível entrar na conta."
                    )

                val emailUsuario =
                    usuarioFirebase.email ?: email.trim()

                _usuario.value = carregarPerfil(
                    uid = usuarioFirebase.uid,
                    email = emailUsuario
                )

                carregarQuantidadeSugestoes()
                carregarQuantidadeConsultas()
                registrarAcessoDiario(usuarioFirebase.uid)

                _sucesso.value = "Login realizado com sucesso"

            } catch (e: Exception) {
                _erro.value = when (
                    (e as? FirebaseAuthException)?.errorCode
                ) {
                    "ERROR_INVALID_EMAIL" ->
                        "Digite um e-mail válido."

                    "ERROR_USER_NOT_FOUND",
                    "ERROR_WRONG_PASSWORD",
                    "ERROR_INVALID_CREDENTIAL" ->
                        "E-mail ou senha incorretos."

                    else ->
                        "Não foi possível entrar. Verifique sua conexão e tente novamente."
                }
            } finally {
                _carregando.value = false
            }
        }
    }

    fun carregarUsuarioAtual() {
        val usuarioFirebase = auth.currentUser ?: return

        viewModelScope.launch {
            try {
                val email = usuarioFirebase.email
                    ?: return@launch

                _usuario.value = carregarPerfil(
                    uid = usuarioFirebase.uid,
                    email = email
                )

                carregarQuantidadeSugestoes()
                carregarQuantidadeConsultas()
                registrarAcessoDiario(usuarioFirebase.uid)

            } catch (e: Exception) {
                _erro.value =
                    "Não foi possível carregar seu perfil."
            }
        }
    }

    fun carregarQuantidadeSugestoes() {
        sugestaoResiduoRepository.contarSugestoesDoUsuario(
            onSuccess = { quantidade ->
                _sugestoesEnviadas.value = quantidade
            },
            onError = {
                _sugestoesEnviadas.value = 0
            }
        )
    }

    fun carregarQuantidadeConsultas() {
        consultaResiduoRepository.contarConsultas(
            onSuccess = { quantidade ->
                _consultasRealizadas.value = quantidade
            },
            onError = {
                _consultasRealizadas.value = 0
            }
        )
    }

    fun limparMensagens() {
        _erro.value = null
        _sucesso.value = null
    }

    fun sair() {
        perfilListener?.remove()
        perfilListener = null

        auth.signOut()

        _usuario.value = null
        _sequenciaDias.value = 0
        _sugestoesEnviadas.value = 0
        _consultasRealizadas.value = 0
        _erro.value = null
        _sucesso.value = null
    }

    suspend fun restaurarSessao(): Boolean {
        val usuarioFirebase = auth.currentUser ?: return false

        return try {
            val email = usuarioFirebase.email ?: return false

            _usuario.value = carregarPerfil(
                uid = usuarioFirebase.uid,
                email = email
            )

            carregarQuantidadeSugestoes()
            carregarQuantidadeConsultas()
            registrarAcessoDiario(usuarioFirebase.uid)

            true

        } catch (e: Exception) {
            _erro.value =
                "Não foi possível carregar seu perfil."
            false
        }
    }

    override fun onCleared() {
        perfilListener?.remove()
        perfilListener = null
        super.onCleared()
    }
}