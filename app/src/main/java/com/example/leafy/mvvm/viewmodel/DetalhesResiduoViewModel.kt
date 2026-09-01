package com.example.leafy.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoRepository

class DetalhesResiduoViewModel:ViewModel(){
    private val repository = ResiduoRepository()
    fun buscarResiduo(id:Int): Residuo?{
        return repository.buscarPorId(id)
    }
}