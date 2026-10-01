package com.example.leafy.mvvm.view.screens.autenticacao

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.viewmodel.UsuarioViewModel

@Composable
fun CadastroScreen(
    onCadastroSucesso: () -> Unit,
    viewModel: UsuarioViewModel = viewModel()
){
    var nome by rememberSaveable { mutableStateOf("")}
    var email by rememberSaveable { mutableStateOf("")}
    var senha by rememberSaveable { mutableStateOf("")}
    
    val carregando by viewModel.carregando.collectAsState()
    val erro by viewModel.erro.collectAsState()
    val sucesso by viewModel.sucesso.collectAsState()
    
    LaunchedEffect(sucesso) {
        if (sucesso != null){
            onCadastroSucesso()
            viewModel.limparMensagens()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Criar conta")
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedTextField(
            value = nome, 
            onValueChange = {nome = it}, 
            label = { Text("Nome")}, 
            modifier = Modifier.fillMaxWidth(), 
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedTextField(
            value = email, 
            onValueChange = {email = it},
            label = { Text("Email")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = {email = it},
            label = { Text("Senha")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Button(
            onClick = {
                viewModel.cadastrar(
                    nome = nome,
                    email = email,
                    senha = senha
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !carregando
        ) {
            if (carregando){
                CircularProgressIndicator()
            } else {
                Text("Criar conta")
            }
        }
        
        if (erro != null){
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = erro ?: "")
        }
    }
}