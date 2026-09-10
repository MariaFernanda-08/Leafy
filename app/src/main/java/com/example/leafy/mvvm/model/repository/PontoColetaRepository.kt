package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.PontoColeta
import com.example.leafy.mvvm.remote.LeafyApi

class PontoColetaRepository(
    private val api: LeafyApi
){
    suspend fun buscarPontos(): List<PontoColeta>{
        return api.buscarPontosColeta()
    }
}