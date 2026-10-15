package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.PontoColeta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PontoColetaRepository{
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun buscarPontos(): List<PontoColeta>{
        val snapshot = firestore
            .collection("pontos_coleta")
            .get()
            .await()

        return snapshot.documents.mapNotNull { documento ->
            val id = documento.getLong("id")?.toInt()
                ?: return@mapNotNull null

            val nome = documento.getString("nome")
                ?: return@mapNotNull null

            val tipo = documento.getString("tipo") ?: ""
            val endereco = documento.getString("endereco") ?: ""

            val latitude = documento.getDouble("latitude")
                ?: return@mapNotNull null

            val longitude = documento.getDouble("longitude")
                ?: return@mapNotNull null

            PontoColeta(
                id = id,
                nome = nome,
                tipo = tipo,
                endereco = endereco,
                latitude = latitude,
                longitude = longitude,
                descricao = documento.getString("descricao"),
                materiais_aceitos = documento.getString("materiaisAceitos"),
                status = documento.getString("status") ?: "",
                horario_funcionamento =
                documento.getString("horarioFuncionamento")
            )
        }
    }
}