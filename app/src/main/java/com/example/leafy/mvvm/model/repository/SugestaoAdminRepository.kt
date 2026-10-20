package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.SugestaoResiduo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SugestaoAdminRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    companion object {
        private const val XP_POR_SUGESTAO = 5
    }

    suspend fun buscarPendentes(): List<SugestaoResiduo> {
        verificarLogin()

        val snapshot = firestore
            .collection("sugestoes_residuos")
            .whereEqualTo("status", "pendente")
            .get()
            .await()

        return snapshot.documents.map { documento ->
            SugestaoResiduo(
                id = documento.id,
                codigoBarras = documento.getString("codigoBarras") ?: "",
                nome = documento.getString("nome") ?: "",
                tipo = documento.getString("tipo") ?: "",
                instrucoesDescarte =
                documento.getString("instrucoesDescarte") ?: "",
                tempoDecomposicao =
                documento.getString("tempoDecomposicao") ?: "",
                impactoAmbiental =
                documento.getString("impactoAmbiental") ?: "",
                reciclavel = documento.getBoolean("reciclavel") ?: false,
                status = documento.getString("status") ?: "pendente",
                usuarioId = documento.getString("usuarioId") ?: "",
                criadoEm = documento.getTimestamp("criadoEm")
            )
        }
    }

    suspend fun aprovar(sugestao: SugestaoResiduo) {
        verificarLogin()

        require(sugestao.codigoBarras.isNotBlank()) {
            "A sugestão não possui código de barras."
        }

        val sugestaoRef = firestore
            .collection("sugestoes_residuos")
            .document(sugestao.id)

        val residuosRef = firestore.collection("residuos")
        val usuarioAdmin = auth.currentUser
            ?: throw IllegalStateException("É necessário entrar na conta.")

        val duplicado = residuosRef
            .whereEqualTo("codigoBarras", sugestao.codigoBarras.trim())
            .limit(1)
            .get()
            .await()

        check(duplicado.isEmpty) {
            "Já existe um resíduo com esse código de barras."
        }

        val todosResiduos = residuosRef.get().await()

        val proximoId = (
                todosResiduos.documents.mapNotNull { doc ->
                    (doc.get("id") as? Number)?.toInt()
                        ?: doc.getString("id")?.toIntOrNull()
                }.maxOrNull() ?: 0
                ) + 1

        firestore.runTransaction { transacao ->

            val documento = transacao.get(sugestaoRef)

            check(documento.exists()) {
                "Esta sugestão não foi encontrada."
            }

            check(documento.getString("status") == "pendente") {
                "Esta sugestão já foi analisada."
            }

            val codigoBarras =
                documento.getString("codigoBarras")?.trim().orEmpty()

            check(codigoBarras.isNotBlank()) {
                "A sugestão não possui código de barras."
            }

            val usuarioId = documento.getString("usuarioId").orEmpty()

            check(usuarioId.isNotBlank()) {
                "Não foi possível identificar quem enviou a sugestão."
            }

            val usuarioRef = firestore
                .collection("usuarios")
                .document(usuarioId)

            val usuarioDocumento = transacao.get(usuarioRef)

            check(usuarioDocumento.exists()) {
                "O perfil do usuário que enviou a sugestão não foi encontrado."
            }

            val residuoRef = residuosRef.document()

            val dadosResiduo = hashMapOf<String, Any>(
                "id" to proximoId,
                "nome" to (documento.getString("nome") ?: "").trim(),
                "codigoBarras" to codigoBarras,
                "tipo" to (documento.getString("tipo") ?: "").trim(),
                "instrucoesDescarte" to
                        (documento.getString("instrucoesDescarte") ?: "").trim(),
                "reciclavel" to (documento.getBoolean("reciclavel") ?: false),
                "tempoDecomposicao" to
                        (documento.getString("tempoDecomposicao") ?: "").trim(),
                "impactoAmbiental" to
                        (documento.getString("impactoAmbiental") ?: "").trim()
            )

            val xpAtual = usuarioDocumento.getLong("xp")?.toInt() ?: 0
            val novoXp = xpAtual + XP_POR_SUGESTAO

            transacao.set(residuoRef, dadosResiduo)

            transacao.update(
                sugestaoRef,
                mapOf(
                    "status" to "aprovada",
                    "xpConcedido" to true,
                    "xpConcedidoValor" to XP_POR_SUGESTAO,
                    "aprovadaEm" to FieldValue.serverTimestamp(),
                    "aprovadaPor" to usuarioAdmin.uid
                )
            )

            transacao.update(
                usuarioRef,
                mapOf(
                    "xp" to novoXp,
                    "nivel" to nivelPorXp(novoXp)
                )
            )

            null
        }.await()
    }

    suspend fun rejeitar(sugestao: SugestaoResiduo) {
        verificarLogin()

        val referencia = firestore
            .collection("sugestoes_residuos")
            .document(sugestao.id)

        firestore.runTransaction { transacao ->
            val documento = transacao.get(referencia)

            check(documento.exists()) {
                "Esta sugestão não foi encontrada."
            }

            check(documento.getString("status") == "pendente") {
                "Esta sugestão já foi analisada."
            }

            transacao.update(
                referencia,
                mapOf(
                    "status" to "rejeitada",
                    "analisadaEm" to FieldValue.serverTimestamp(),
                    "analisadaPor" to auth.currentUser!!.uid
                )
            )

            null
        }.await()
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

    private fun verificarLogin() {
        check(auth.currentUser != null) {
            "É necessário entrar na conta."
        }
    }
}