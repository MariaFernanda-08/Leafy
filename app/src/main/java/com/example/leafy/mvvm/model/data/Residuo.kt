package com.example.leafy.mvvm.model.data

import com.google.gson.annotations.SerializedName

data class Residuo(
    val id: Int,
    val nome: String,

    @SerializedName("codigo_barras")
    val codigoBarras: String?,

    val tipo: String,

    @SerializedName("instrucoes_descarte")
    val instrucoesDescarte: String,

    val reciclavel: Boolean,

    @SerializedName("tempo_decomposicao")
    val tempoDecomposicao: String,

    @SerializedName("impacto_ambiental")
    val impactoAmbiental: String
)