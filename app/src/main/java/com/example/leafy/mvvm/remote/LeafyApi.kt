package com.example.leafy.mvvm.remote

import com.example.leafy.mvvm.model.data.Residuo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LeafyApi {
    @GET("residuos") //busca todos os residuos
    suspend fun bsucarResiduos(): List<Residuo>

    @GET("residuos/{id}") // busca resíduo pelo ID
    suspend fun buscarResiduosPorId(
        @Path("id") id:Int
    ): Residuo

    @GET("residuos/buscar") //busca resíduo por nome
    suspend fun pesquisarResiduos(
        @Query("nome") nome: String
    ): List<Residuo>

    @GET("residuos/codigo/{codigo}")
    suspend fun buscarPorCodigo(
        @Path("codigo") codigo:String
    ): Residuo
}