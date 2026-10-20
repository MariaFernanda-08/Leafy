package com.example.leafy.mvvm.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ResiduoFirebaseRepository
import java.text.Normalizer
import java.util.Locale

class ConsultaViewModel : ViewModel() {

    private val repository = ResiduoFirebaseRepository()

    var pesquisa by mutableStateOf("")
        private set

    var resultados by mutableStateOf<List<Residuo>>(emptyList())
        private set

    var categoriaSelecionada by mutableStateOf<String?>(null)
        private set

    private fun normalizar(texto: String): String {
        return Normalizer
            .normalize(texto, Normalizer.Form.NFD)
            .replace("\\p{Mn}+".toRegex(), "")
            .lowercase(Locale.ROOT)
            .trim()
    }

    fun atualizarPesquisa(texto: String) {
        pesquisa = texto
        categoriaSelecionada = null

        if (texto.isBlank()) {
            resultados = emptyList()
            return
        }

        val termo = normalizar(texto)

        repository.buscarResiduos(
            onSuccess = { residuos ->
                resultados = residuos.filter { residuo ->
                    normalizar(residuo.nome).contains(termo) ||
                            normalizar(residuo.tipo).contains(termo) ||
                            normalizar(residuo.codigoBarras.orEmpty()).contains(termo)
                }
            },
            onError = { erro ->
                resultados = emptyList()
                Log.e(
                    "LEAFY_FIREBASE",
                    "Erro ao buscar resíduos",
                    erro
                )
            }
        )
    }

    fun filtrarPorCategoria(categoria: String) {
        repository.buscarResiduos(
            onSuccess = { residuos ->
                resultados = residuos.filter { residuo ->
                    normalizar(residuo.tipo) == normalizar(categoria)
                }

                pesquisa = categoria
                categoriaSelecionada = categoria
            },
            onError = { erro ->
                resultados = emptyList()
                Log.e(
                    "LEAFY_FIREBASE",
                    "Erro ao filtrar resíduos",
                    erro
                )
            }
        )
    }

    fun limparFiltro() {
        pesquisa = ""
        resultados = emptyList()
        categoriaSelecionada = null
    }
}