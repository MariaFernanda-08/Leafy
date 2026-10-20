package com.example.leafy.mvvm.model.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class ConsultaResiduoRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun registrarConsulta(
        residuoId: Int,
        nomeResiduo: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val usuario = auth.currentUser

        if (usuario == null) {
            onSuccess()
            return
        }

        val perfil = firestore.collection("usuarios")
            .document(usuario.uid)

        val novaConsulta = firestore.collection("consultas_residuos")
            .document()

        firestore.runTransaction { transacao ->
            val documento = transacao.get(perfil)

            if (!documento.exists() ||
                documento.getString("tipoUsuario") != "usuario"
            ) {
                return@runTransaction false
            }

            val xpAtual = documento.getLong("xp")?.toInt() ?: 0

            val dadosConsulta = hashMapOf<String, Any>(
                "usuarioId" to usuario.uid,
                "residuoId" to residuoId,
                "nomeResiduo" to nomeResiduo,
                "criadoEm" to FieldValue.serverTimestamp()
            )

            transacao.set(novaConsulta, dadosConsulta)
            transacao.update(perfil, "xp", xpAtual + 10)

            true
        }.addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener { erro ->
            onError(erro)
        }
    }

    fun contarConsultas(
        onSuccess: (Int) -> Unit,
        onError: (Exception) -> Unit
    ) {
        val usuario = auth.currentUser

        if (usuario == null) {
            onSuccess(0)
            return
        }

        firestore.collection("consultas_residuos")
            .whereEqualTo("usuarioId", usuario.uid)
            .get()
            .addOnSuccessListener { resultado ->
                onSuccess(resultado.size())
            }
            .addOnFailureListener { erro ->
                onError(erro)
            }
    }
}