package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.remote.LeafyApi

class ResiduoRepository(private val api: LeafyApi)
{
    suspend fun buscarResiduos(): List<Residuo>{ //todos os residuos
        return api.bsucarResiduos()
    }

    suspend fun buscarResiduosPorId(id:Int): Residuo{ // residuo por id
        return api.buscarResiduosPorId(id)
    }

    suspend fun pesquisarResiduos(nome: String): List<Residuo>{ // residuo pelo nome
        return api.pesquisarResiduos(nome)
    }

    suspend fun buscarPorCodigo(codigo:String): Residuo{
        return api.buscarPorCodigo(codigo)
    }
}