package com.example.leafy.mvvm.model.data

import com.google.firebase.Timestamp

data class SugestaoResiduo(
    val id: String = "",
    val codigoBarras: String = "",
    val nome: String = "",
    val tipo: String = "",
    val instrucoesDescarte: String = "",
    val tempoDecomposicao: String = "",
    val impactoAmbiental: String = "",
    val reciclavel: Boolean = false,
    val status: String = "pendente",
    val usuarioId: String = "",
    val criadoEm: Timestamp? = null
)