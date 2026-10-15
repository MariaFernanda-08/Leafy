package com.example.leafy.mvvm.model.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class SugestaoResiduoRepository {

    private val firestore = FirebaseFirestore.getInstance()

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
        val usuario = FirebaseAuth.getInstance().currentUser

        if (usuario == null) {
            onError(IllegalStateException("Entre na sua conta para enviar uma sugestão."))
            return
        }

        val dados = hashMapOf<String, Any>(
            "codigoBarras" to codigoBarras,
            "nome" to nome.trim(),
            "tipo" to tipo.trim(),
            "instrucoesDescarte" to instrucoesDescarte.trim(),
            "tempoDecomposicao" to tempoDecomposicao.trim(),
            "impactoAmbiental" to impactoAmbiental.trim(),
            "reciclavel" to reciclavel,
            "status" to "pendente",
            "criadoEm" to FieldValue.serverTimestamp(),
            "usuarioId" to usuario.uid
        )

        firestore.collection("sugestoes_residuos")
            .add(dados)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { erro -> onError(erro) }
    }
}