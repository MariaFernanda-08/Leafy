package com.example.leafy.mvvm.remote

import com.example.leafy.mvvm.model.data.PontoColeta
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.data.Usuario
import com.example.leafy.mvvm.model.data.CadastroRequest
import com.example.leafy.mvvm.model.data.LoginRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.POST

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

    @GET("pontos-coleta")
    suspend fun buscarPontosColeta(): List<PontoColeta>

    @POST("users/cadastro")
    suspend fun cadastrarUsuario(
        @Body usuario: CadastroRequest
    ): Usuario

    @POST("users/login")
    suspend fun fazerLogin(
        @Body usuario: LoginRequest
    ): Usuario
}