package com.example.leafy.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoRepository
import com.example.leafy.mvvm.remote.RetrofitClient
import kotlinx.coroutines.launch
import android.util.Log

class ScannerViewModel : ViewModel() {
    private val repository = ResiduoRepository(RetrofitClient.api)

    fun buscarCodigo(
        codigo:String,
        onResultado: (Residuo?) -> Unit)
    {
        viewModelScope.launch {
            try {
                Log.d("LEAFY_API", "Buscando código: $codigo")

                val residuo = repository.buscarPorCodigo(codigo)

                Log.d(
                    "LEAFY_API",
                    "Produto encontrado: ${residuo.nome}, ID: ${residuo.id}"
                )

                onResultado(residuo)

            } catch (e: Exception) {

                Log.e(
                    "LEAFY_API",
                    "Erro ao buscar produto",
                    e
                )

                onResultado(null)
        }
        }
    }
}