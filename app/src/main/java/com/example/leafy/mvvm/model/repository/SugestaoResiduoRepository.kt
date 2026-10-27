package com.example.leafy.mvvm.model.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class SugestaoResiduoRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    companion object {
        private const val XP_SUGESTAO_APROVADA = 5
    }

    fun enviarSugestao(
        codigoBarras: String,
        nome: String,
        tipo: String,
        instrucoesDescarte: String,
        tempoDecomposicao: String,
        impactoAmbiental: String,
        reciclavel: Boolean,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val usuario = auth.currentUser

        if (usuario == null) {
            onError(
                IllegalStateException(
                    "Entre na sua conta para enviar uma sugestão."
                )
            )
            return
        }

        val dados = hashMapOf<String, Any>(
            "codigoBarras" to codigoBarras.trim(),
            "nome" to nome.trim(),
            "tipo" to tipo.trim(),
            "instrucoesDescarte" to instrucoesDescarte.trim(),
            "tempoDecomposicao" to tempoDecomposicao.trim(),
            "impactoAmbiental" to impactoAmbiental.trim(),
            "reciclavel" to reciclavel,
            "status" to "pendente",
            "criadoEm" to FieldValue.serverTimestamp(),
            "usuarioId" to usuario.uid,
            "xpConcedido" to false
        )

        firestore.collection("sugestoes_residuos")
            .add(dados)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { erro ->
                onError(erro)
            }
    }

    fun contarSugestoesDoUsuario(
        onSuccess: (Int) -> Unit,
        onError: (Exception) -> Unit
    ) {
        val usuario = auth.currentUser

        if (usuario == null) {
            onError(
                IllegalStateException(
                    "Entre na sua conta para consultar suas sugestões."
                )
            )
            return
        }

        firestore.collection("sugestoes_residuos")
            .whereEqualTo("usuarioId", usuario.uid)
            .whereEqualTo("status", "aprovada")
            .get()
            .addOnSuccessListener { resultado ->
                onSuccess(resultado.size())
            }
            .addOnFailureListener { erro ->
                onError(erro)
            }
    }

    fun aprovarSugestao(
        sugestaoId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val sugestaoRef = firestore
            .collection("sugestoes_residuos")
            .document(sugestaoId)

        firestore.runTransaction { transacao ->

            val sugestao = transacao.get(sugestaoRef)

            check(sugestao.exists()) {
                "Sugestão não encontrada."
            }

            val status = sugestao.getString("status") ?: "pendente"

            if (status == "aprovada") {
                return@runTransaction false
            }

            check(status == "pendente") {
                "Esta sugestão não pode ser aprovada."
            }

            val usuarioId = sugestao.getString("usuarioId")
                ?: throw IllegalStateException(
                    "Não foi possível identificar quem enviou a sugestão."
                )

            val usuarioRef = firestore
                .collection("usuarios")
                .document(usuarioId)

            val usuarioDoc = transacao.get(usuarioRef)

            check(usuarioDoc.exists()) {
                "Perfil do usuário não encontrado."
            }

            val xpJaConcedido =
                sugestao.getBoolean("xpConcedido") ?: false

            transacao.update(
                sugestaoRef,
                mapOf(
                    "status" to "aprovada",
                    "aprovadaEm" to FieldValue.serverTimestamp(),
                    "xpConcedido" to true
                )
            )

            if (!xpJaConcedido) {
                val xpAtual =
                    usuarioDoc.getLong("xp")?.toInt() ?: 0

                val novoXp = xpAtual + XP_SUGESTAO_APROVADA

                val novoNivel = when {
                    novoXp < 100 -> "Formiga Operária"
                    novoXp < 300 -> "Castor Engenheiro"
                    novoXp < 600 -> "Lobo Guardião"
                    novoXp < 1000 -> "Condor dos Andes"
                    novoXp < 1500 -> "Tartaruga Milenar"
                    else -> "Fênix"
                }

                transacao.update(
                    usuarioRef,
                    mapOf(
                        "xp" to novoXp,
                        "nivel" to novoNivel
                    )
                )
            }

            true
        }.addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener { erro ->
            onError(erro)
        }
    }

    fun rejeitarSugestao(
        sugestaoId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val sugestaoRef = firestore
            .collection("sugestoes_residuos")
            .document(sugestaoId)

        firestore.runTransaction { transacao ->

            val sugestao = transacao.get(sugestaoRef)

            check(sugestao.exists()) {
                "Sugestão não encontrada."
            }

            val status = sugestao.getString("status") ?: "pendente"

            check(status == "pendente") {
                "Esta sugestão já foi analisada."
            }

            transacao.update(
                sugestaoRef,
                mapOf(
                    "status" to "rejeitada",
                    "analisadaEm" to FieldValue.serverTimestamp()
                )
            )

            true
        }.addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener { erro ->
            onError(erro)
        }
    }
}