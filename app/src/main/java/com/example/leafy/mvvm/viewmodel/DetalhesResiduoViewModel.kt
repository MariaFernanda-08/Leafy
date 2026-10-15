package com.example.leafy.mvvm.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoFirebaseRepository

class DetalhesResiduoViewModel : ViewModel() {

    private val repository = ResiduoFirebaseRepository()

    var residuo by mutableStateOf<Residuo?>(null)
        private set

    fun buscarResiduo(id: Int) {
        residuo = null

        repository.buscarResiduos(
            onSuccess = { residuos ->
                residuo = residuos.find { it.id == id }

                if (residuo == null) {
                    Log.e(
                        "LEAFY_DETALHES",
                        "Resíduo com ID $id não encontrado no Firebase"
                    )
                } else {
                    Log.d(
                        "LEAFY_DETALHES",
                        "Resíduo carregado: ${residuo?.nome}"
                    )
                }
            },
            onError = { erro ->
                residuo = null
                Log.e(
                    "LEAFY_DETALHES",
                    "Erro ao carregar resíduo do Firebase",
                    erro
                )
            }
        )
    }
}