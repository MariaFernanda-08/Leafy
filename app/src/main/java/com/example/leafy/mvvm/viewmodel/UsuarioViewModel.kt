package com.example.leafy.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafy.mvvm.model.data.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
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

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    private val _carregando = MutableStateFlow(false)
    val carregando: StateFlow<Boolean> = _carregando

    private val _erro = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _erro

    private val _sucesso = MutableStateFlow<String?>(null)
    val sucesso: StateFlow<String?> = _sucesso

    private fun dataAtual(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.getDefault()
        ).format(Date())
    }

    private suspend fun carregarPerfil(
        uid: String,
        email: String
    ): Usuario {
        val referencia = firestore
            .collection("usuarios")
            .document(uid)

        val documento = referencia.get().await()

        val nome = documento.getString("nome")
            ?: auth.currentUser?.displayName
            ?: email.substringBefore("@")

        val xp = documento.getLong("xp")?.toInt() ?: 0
        val nivel = documento.getString("nivel") ?: "Nível 1"

        val criadoEm = documento.getString("criado_em")
            ?: documento.getString("criadoEm")
            ?: dataAtual()

        val tipoUsuario = documento.getString("tipoUsuario")
            ?: "usuario"

        if (!documento.exists()) {
            referencia.set(
                mapOf(
                    "nome" to nome,
                    "email" to email,
                    "xp" to xp,
                    "nivel" to nivel,
                    "criado_em" to criadoEm,
                    "tipoUsuario" to "usuario"
                )
            ).await()
        }

        return Usuario(
            id = uid.hashCode(),
            nome = nome,
            email = email,
            xp = xp,
            nivel = nivel,
            criadoEm = criadoEm,
            tipoUsuario = if (documento.exists()) {
                tipoUsuario
            } else {
                "usuario"
            }
        )
    }

    fun cadastrar(nome: String, email: String, senha: String) {
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
                    ?: throw Exception("Não foi possível criar a conta.")

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
                            "nivel" to "Nível 1",
                            "criado_em" to data,
                            "tipoUsuario" to "usuario"
                        )
                    )
                    .await()

                _usuario.value = Usuario(
                    id = uid.hashCode(),
                    nome = nomeLimpo,
                    email = emailLimpo,
                    xp = 0,
                    nivel = "Nível 1",
                    criadoEm = data,
                    tipoUsuario = "usuario"
                )

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

    fun login(email: String, senha: String) {
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
                    ?: throw Exception("Não foi possível entrar na conta.")

                val emailUsuario = usuarioFirebase.email
                    ?: email.trim()

                _usuario.value = carregarPerfil(
                    uid = usuarioFirebase.uid,
                    email = emailUsuario
                )

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
                val email = usuarioFirebase.email ?: return@launch

                _usuario.value = carregarPerfil(
                    uid = usuarioFirebase.uid,
                    email = email
                )
            } catch (e: Exception) {
                _erro.value = "Não foi possível carregar seu perfil."
            }
        }
    }

    fun limparMensagens() {
        _erro.value = null
        _sucesso.value = null
    }

    fun sair() {
        auth.signOut()
        _usuario.value = null
        _erro.value = null
        _sucesso.value = null
    }
}