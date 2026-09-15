package com.example.leafy.mvvm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoRepository
import com.example.leafy.mvvm.remote.RetrofitClient
import kotlinx.coroutines.launch


class ConsultaViewModel : ViewModel(){
    private val repository = ResiduoRepository(RetrofitClient.api)

    var pesquisa by mutableStateOf("")
        private set

    var resultados by mutableStateOf<List<Residuo>>(emptyList())
        private set

    var categoriaSelecionada by mutableStateOf<String?>(null)
        private set

    fun atualizarPesquisa(texto: String){
        pesquisa = texto
        viewModelScope.launch {
            try {
                resultados = if(texto.isBlank()){
                    emptyList()
                } else{
                    repository.pesquisarResiduos(texto)
                }
            } catch (e: Exception){
                resultados = emptyList()
                android.util.Log.e("LEAFY_API", "Erro ao buscar resíduos", e)
                // e.printStackTrace()
            }
        }
    }


    fun filtrarPorCategoria(categoria: String) {
        viewModelScope.launch {
            try {
                val residuos = repository.buscarResiduos()

                resultados = residuos.filter {
                    it.tipo.equals(categoria, ignoreCase = true)
                }

                pesquisa = categoria
                categoriaSelecionada = categoria
            } catch (e: Exception) {
                resultados = emptyList()
                android.util.Log.e(
                    "LEAFY_API",
                    "Erro ao filtrar categoria",
                    e
                )
            }
        }
    }

    fun limparFiltro() {
        pesquisa = ""
        resultados = emptyList()
        categoriaSelecionada = null
    }

}