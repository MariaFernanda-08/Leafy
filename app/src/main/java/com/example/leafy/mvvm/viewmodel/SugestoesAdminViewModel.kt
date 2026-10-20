package com.example.leafy.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafy.mvvm.model.data.SugestaoResiduo
import com.example.leafy.mvvm.model.repository.SugestaoAdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SugestoesAdminViewModel : ViewModel() {

    private val repository = SugestaoAdminRepository()

    private val _sugestoes =
        MutableStateFlow<List<SugestaoResiduo>>(emptyList())
    val sugestoes: StateFlow<List<SugestaoResiduo>> =
        _sugestoes.asStateFlow()

    private val _carregando = MutableStateFlow(false)
    val carregando: StateFlow<Boolean> =
        _carregando.asStateFlow()

    private val _mensagem = MutableStateFlow<String?>(null)
    val mensagem: StateFlow<String?> =
        _mensagem.asStateFlow()

    fun carregarPendentes() {
        viewModelScope.launch {
            _carregando.value = true
            _mensagem.value = null

            try {
                atualizarLista()
            } catch (e: Exception) {
                _mensagem.value =
                    e.message
                        ?: "Não foi possível carregar as sugestões."
            } finally {
                _carregando.value = false
            }
        }
    }

    fun aprovar(sugestao: SugestaoResiduo) {
        viewModelScope.launch {
            _carregando.value = true
            _mensagem.value = null

            try {
                repository.aprovar(sugestao)

                atualizarLista()

                _mensagem.value =
                    "Sugestão aprovada com sucesso!"

            } catch (e: Exception) {
                _mensagem.value =
                    e.message
                        ?: "Não foi possível aprovar a sugestão."
            } finally {
                _carregando.value = false
            }
        }
    }

    fun rejeitar(sugestao: SugestaoResiduo) {
        viewModelScope.launch {
            _carregando.value = true
            _mensagem.value = null

            try {
                repository.rejeitar(sugestao)

                atualizarLista()

                _mensagem.value = "Sugestão rejeitada."

            } catch (e: Exception) {
                _mensagem.value =
                    e.message
                        ?: "Não foi possível rejeitar a sugestão."
            } finally {
                _carregando.value = false
            }
        }
    }

    private suspend fun atualizarLista() {
        _sugestoes.value = repository.buscarPendentes()
    }

    fun limparMensagem() {
        _mensagem.value = null
    }
}