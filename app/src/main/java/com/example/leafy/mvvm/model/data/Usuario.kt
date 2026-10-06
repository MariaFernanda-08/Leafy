package com.example.leafy.mvvm.model.data

import com.google.gson.annotations.SerializedName

data class Usuario(
    val id: Int,
    val nome: String,
    val email: String,
    val xp: Int,
    val nivel: String,

    @SerializedName("criado_em")
    val criadoEm: String
)