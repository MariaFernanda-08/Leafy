package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.SugestaoResiduo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SugestaoAdminRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

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

        // Confere se o código de barras já existe.
        val duplicado = firestore
            .collection("residuos")
            .whereEqualTo("codigoBarras", sugestao.codigoBarras)
            .limit(1)
            .get()
            .await()

        if (!duplicado.isEmpty) {
            throw IllegalStateException(
                "Já existe um resíduo com esse código de barras."
            )
        }

        // Obtém o próximo ID numérico usado pelo aplicativo.
        val residuosSnapshot = firestore
            .collection("residuos")
            .get()
            .await()

        val proximoId = (residuosSnapshot.documents
            .mapNotNull { documento ->
                (documento.get("id") as? Number)?.toInt()
                    ?: documento.getString("id")?.toIntOrNull()
            }
            .maxOrNull() ?: 0) + 1

        val residuoRef = firestore
            .collection("residuos")
            .document()

        val dadosResiduo = hashMapOf<String, Any>(
            "id" to proximoId,
            "nome" to sugestao.nome,
            "codigoBarras" to sugestao.codigoBarras,
            "tipo" to sugestao.tipo,
            "instrucoesDescarte" to sugestao.instrucoesDescarte,
            "reciclavel" to sugestao.reciclavel,
            "tempoDecomposicao" to sugestao.tempoDecomposicao,
            "impactoAmbiental" to sugestao.impactoAmbiental
        )

        // O cadastro e a aprovação acontecem juntos.
        firestore.runTransaction { transacao ->
            val documento = transacao.get(sugestaoRef)

            check(documento.exists()) {
                "Esta sugestão não foi encontrada."
            }

            check(documento.getString("status") == "pendente") {
                "Esta sugestão já foi analisada."
            }

            transacao.set(residuoRef, dadosResiduo)
            transacao.update(sugestaoRef, "status", "aprovada")
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

            transacao.update(referencia, "status", "rejeitada")
        }.await()
    }

    private fun verificarLogin() {
        check(auth.currentUser != null) {
            "É necessário entrar na conta."
        }
    }
}