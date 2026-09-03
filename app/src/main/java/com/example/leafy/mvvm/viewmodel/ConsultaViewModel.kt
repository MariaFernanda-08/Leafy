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
}