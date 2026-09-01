package com.example.leafy.mvvm.model.data

data class Residuo(
    val id: Int,
    val nome: String,
    val codigoBarras: String?,
    val tipo: String,
    val instrucoesDescarte: String,
    val reciclavel: Boolean,
    val tempoDecomposicao: String,
    val impactoAmbiental: String
)