package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.CadastroRequest
import com.example.leafy.mvvm.model.data.LoginRequest
import com.example.leafy.mvvm.model.data.Usuario
import com.example.leafy.mvvm.remote.LeafyApi

class UsuarioRepository(
    private val api: LeafyApi
) {
    suspend fun cadastrarUsuario(
        usuario: CadastroRequest
    ): Usuario {
        return api.cadastrarUsuario(usuario)
    }

    suspend fun fazerLogin(
        usuario: LoginRequest
    ): Usuario {
        return api.fazerLogin(usuario)
    }
}