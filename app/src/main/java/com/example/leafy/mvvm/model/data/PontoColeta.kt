package com.example.leafy.mvvm.model.data

data class PontoColeta (
    val id: Int,
    val nome: String,
    val tipo: String,
    val endereco: String,
    val latitude: Double,
    val longitude: Double,
    val descricao: String?,
    val materiais_aceitos: String?,
    val status: String
)
