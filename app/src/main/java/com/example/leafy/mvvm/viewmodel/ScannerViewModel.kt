package com.example.leafy.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoFirebaseRepository

class ScannerViewModel : ViewModel() {

    private val repository = ResiduoFirebaseRepository()

    fun buscarCodigo(
        codigo: String,
        onResultado: (Residuo?) -> Unit
    ) {
        Log.d("LEAFY_SCANNER", "Buscando código no Firebase: $codigo")

        repository.buscarPorCodigo(
            codigo = codigo,
            onSuccess = { residuo ->
                if (residuo != null) {
                    Log.d(
                        "LEAFY_SCANNER",
                        "Produto encontrado: ${residuo.nome}, ID: ${residuo.id}"
                    )
                } else {
                    Log.d(
                        "LEAFY_SCANNER",
                        "Nenhum produto encontrado para o código: $codigo"
                    )
                }

                onResultado(residuo)
            },
            onError = { erro ->
                Log.e(
                    "LEAFY_SCANNER",
                    "Erro ao buscar produto no Firebase",
                    erro
                )

                onResultado(null)
            }
        )
    }
}