package com.example.leafy.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafy.mvvm.model.data.PontoColeta
import com.example.leafy.mvvm.model.repository.PontoColetaRepository
import com.example.leafy.mvvm.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PontoColetaViewModel : ViewModel(){
    private val repository =
        PontoColetaRepository(RetrofitClient.api)
    private val _pontos = MutableStateFlow<List<PontoColeta>>(emptyList())
    val pontos: StateFlow<List<PontoColeta>> = _pontos

    fun buscarPontos(){
        viewModelScope.launch {
            try {
                Log.d("LEAFY_MAPA", "Buscando pontos de coleta...")
                val resultado = repository.buscarPontos()
                _pontos.value = resultado
                Log.d("LEAFY_MAPA", "Pontos encontrados: ${resultado.size}")
            } catch (e: Exception){
                Log.e("LEAFY_MAPA", "Erro ao buscar pontos de coleta", e)
            }
        }
    }
}