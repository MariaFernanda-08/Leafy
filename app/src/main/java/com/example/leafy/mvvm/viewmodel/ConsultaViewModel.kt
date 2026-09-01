package com.example.leafy.mvvm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoRepository

class ConsultaViewModel : ViewModel(){
    private val repository = ResiduoRepository()

    var pesquisa by mutableStateOf("")
        private set

    var resultados by mutableStateOf<List<Residuo>>(emptyList())
        private set

    fun atualizarPesquisa(texto: String){
        pesquisa = texto
        resultados = repository.pesquisar(texto)
    }
}