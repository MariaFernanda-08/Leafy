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

class DetalhesResiduoViewModel:ViewModel(){
    private val repository = ResiduoRepository(RetrofitClient.api)

    var residuo by mutableStateOf<Residuo?>(null)
        private set

    fun buscarResiduo(id:Int){
        viewModelScope.launch {
            try {
                residuo = repository.buscarResiduosPorId(id)
            } catch (e: Exception){
                residuo = null
                e.printStackTrace()
            }
        }
    }
}