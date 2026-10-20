package com.example.leafy.mvvm.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.model.repository.ConsultaResiduoRepository
import com.example.leafy.mvvm.model.repository.ResiduoFirebaseRepository

class DetalhesResiduoViewModel : ViewModel() {

    private val repository = ResiduoFirebaseRepository()
    private val consultaRepository = ConsultaResiduoRepository()

    var residuo by mutableStateOf<Residuo?>(null)
        private set

    private var consultaRegistradaParaId: Int? = null
    private var consultaEmAndamentoParaId: Int? = null

    fun buscarResiduo(id: Int) {
        residuo = null

        repository.buscarResiduos(
            onSuccess = { residuos ->
                val encontrado = residuos.find { it.id == id }
                residuo = encontrado

                if (encontrado == null) {
                    Log.e(
                        "LEAFY_DETALHES",
                        "Resíduo com ID $id não encontrado no Firebase"
                    )
                } else {
                    Log.d(
                        "LEAFY_DETALHES",
                        "Resíduo carregado: ${encontrado.nome}"
                    )

                    registrarConsultaUmaVez(encontrado)
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

    private fun registrarConsultaUmaVez(residuo: Residuo) {
        val id = residuo.id

        if (consultaRegistradaParaId == id ||
            consultaEmAndamentoParaId == id
        ) {
            return
        }

        consultaEmAndamentoParaId = id

        consultaRepository.registrarConsulta(
            residuoId = id,
            nomeResiduo = residuo.nome,
            onSuccess = {
                consultaRegistradaParaId = id
                consultaEmAndamentoParaId = null
            },
            onError = { erro ->
                consultaEmAndamentoParaId = null
                Log.e(
                    "LEAFY_CONSULTAS",
                    "Não foi possível registrar a consulta",
                    erro
                )
            }
        )
    }
}