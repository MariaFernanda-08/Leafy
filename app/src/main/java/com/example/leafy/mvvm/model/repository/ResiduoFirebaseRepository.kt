package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.Residuo
import com.google.firebase.firestore.FirebaseFirestore

class ResiduoFirebaseRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun buscarResiduos(
        onSuccess: (List<Residuo>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        firestore.collection("residuos")
            .get()
            .addOnSuccessListener { documentos ->

                val residuos = documentos.documents.map { documento ->
                    Residuo(
                        id = when(val valorId = documento.get("id")){
                            is Number -> valorId.toInt()
                            is String -> valorId.toIntOrNull() ?: 0
                            else -> 0
                        },
                        nome = documento.getString("nome") ?: "",
                        codigoBarras = documento.getString("codigoBarras"),
                        tipo = documento.getString("tipo") ?: "",
                        instrucoesDescarte =
                        documento.getString("instrucoesDescarte") ?: "",
                        reciclavel =
                        documento.getBoolean("reciclavel") ?: false,
                        tempoDecomposicao =
                        documento.getString("tempoDecomposicao") ?: "",
                        impactoAmbiental =
                        documento.getString("impactoAmbiental") ?: "",
                        explicacaoReciclagem =
                        documento.getString("explicacaoReciclagem"),
                        dicaDescarte =
                        documento.getString("dicaDescarte"),
                        curiosidade =
                        documento.getString("curiosidade")
                    )
                }

                onSuccess(residuos)
            }
            .addOnFailureListener { erro ->
                onError(erro)
            }
    }

    fun buscarPorCodigo(
        codigo: String,
        onSuccess: (Residuo?) -> Unit,
        onError: (Exception) -> Unit
    ) {
        firestore.collection("residuos")
            .whereEqualTo("codigoBarras", codigo)
            .get()
            .addOnSuccessListener { documentos ->
                val documento = documentos.documents.firstOrNull()

                if (documento == null) {
                    onSuccess(null)
                    return@addOnSuccessListener
                }

                val residuo = Residuo(
                    id = when (val valorId = documento.get("id")) {
                        is Number -> valorId.toInt()
                        is String -> valorId.toIntOrNull() ?: 0
                        else -> 0
                    },
                    nome = documento.getString("nome") ?: "",
                    codigoBarras = documento.getString("codigoBarras"),
                    tipo = documento.getString("tipo") ?: "",
                    instrucoesDescarte =
                    documento.getString("instrucoesDescarte") ?: "",
                    reciclavel =
                    documento.getBoolean("reciclavel") ?: false,
                    tempoDecomposicao =
                    documento.getString("tempoDecomposicao") ?: "",
                    impactoAmbiental =
                    documento.getString("impactoAmbiental") ?: "",
                    explicacaoReciclagem =
                    documento.getString("explicacaoReciclagem"),
                    dicaDescarte =
                    documento.getString("dicaDescarte"),
                    curiosidade =
                    documento.getString("curiosidade")
                )

                onSuccess(residuo)
            }
            .addOnFailureListener { erro ->
                onError(erro)
            }
    }
}