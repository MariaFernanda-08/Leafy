package com.example.leafy.mvvm.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoFirebaseRepository

class ConsultaViewModel : ViewModel() {

    private val repository = ResiduoFirebaseRepository()

    var pesquisa by mutableStateOf("")
        private set

    var resultados by mutableStateOf<List<Residuo>>(emptyList())
        private set

    var categoriaSelecionada by mutableStateOf<String?>(null)
        private set

    fun atualizarPesquisa(texto: String) {
        pesquisa = texto
        categoriaSelecionada = null

        if (texto.isBlank()) {
            resultados = emptyList()
            return
        }

        repository.buscarResiduos(
            onSuccess = { residuos ->
                resultados = residuos.filter {
                    it.nome.contains(texto, ignoreCase = true)
                }
            },
            onError = { erro ->
                resultados = emptyList()
                Log.e("LEAFY_FIREBASE", "Erro ao buscar resíduos", erro)
            }
        )
    }

    fun filtrarPorCategoria(categoria: String) {
        repository.buscarResiduos(
            onSuccess = { residuos ->
                resultados = residuos.filter {
                    it.tipo.equals(categoria, ignoreCase = true)
                }

                pesquisa = categoria
                categoriaSelecionada = categoria
            },
            onError = { erro ->
                resultados = emptyList()
                Log.e("LEAFY_FIREBASE", "Erro ao filtrar resíduos", erro)
            }
        )
    }

    fun limparFiltro() {
        pesquisa = ""
        resultados = emptyList()
        categoriaSelecionada = null
    }
}