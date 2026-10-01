package com.example.leafy.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leafy.mvvm.model.data.CadastroRequest
import com.example.leafy.mvvm.model.data.LoginRequest
import com.example.leafy.mvvm.model.data.Usuario
import com.example.leafy.mvvm.remote.RetrofitClient
import com.example.leafy.mvvm.model.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {
    private val repository = UsuarioRepository(RetrofitClient.api)

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    private val _carregando = MutableStateFlow(false)
    val carregando: StateFlow<Boolean> = _carregando

    private val _erro = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _erro

    private val _sucesso = MutableStateFlow<String?>(null)
    val sucesso: StateFlow<String?> = _sucesso

    fun cadastrar(nome: String, email: String, senha: String){
        viewModelScope.launch {
            _carregando.value = true
            _erro.value = null
            _sucesso.value = null

            try {
                val novoUsuario = CadastroRequest(
                    nome = nome,
                    email = email,
                    senha = senha
                )

                val usuario = repository.cadastrarUsuario(novoUsuario)

                _usuario.value = usuario
                _sucesso.value = "Cadastro realizado com sucesso"
            } catch (e: Exception) {
                _erro.value = "Erro ao realizar cadastro"
            } finally {
                _carregando.value = false
            }
        }
    }

    fun login(email: String, senha: String){
        viewModelScope.launch {
            _carregando.value = true
            _erro.value = null
            _sucesso.value = null

            try {
                val loginRequest = LoginRequest(
                    email = email,
                    senha = senha
                )

                val usuario = repository.fazerLogin(loginRequest)

                _usuario.value = usuario
                _sucesso.value = "Login realizado com sucesso"
            } catch (e: Exception) {
                _erro.value = "Email ou senha incorretos"
            } finally {
                _carregando.value = false
            }
        }
    }

    fun limparMensagens(){
        _erro.value = null
        _sucesso.value = null
    }
}