package com.example.leafy.mvvm.view.screens.autenticacao

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.R
import com.example.leafy.mvvm.viewmodel.UsuarioViewModel
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

@Composable
fun LoginScreen(
    onLoginSucesso: () -> Unit,
    onCadastroClick: () -> Unit,
    viewModel: UsuarioViewModel = viewModel()
){
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var mostrarSenha by rememberSaveable { mutableStateOf(false) }
    
    val carregando by viewModel.carregando.collectAsState()
    val erro by viewModel.erro.collectAsState()
    val sucesso by viewModel.sucesso.collectAsState()
    
    LaunchedEffect(sucesso) {
        if(sucesso != null){
            onLoginSucesso()
            viewModel.limparMensagens()
        }
    }

    val verdeLeafy = Color(0xFF00C66B)
    val verdeEscuro = Color(0xFF00A968)
    val fundoCampo = Color(0xFFF8F9FA)
    val cinzaTexto = Color(0xFF7A7A7A)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(verdeLeafy)
    ) {
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.leafy_logo),
            contentDescription = "Logo Leafy",
            modifier = Modifier.size(78.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Leafy",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Faça crescer um futuro melhor!",
            color = Color.White,
            fontSize = 13.sp
        )
    }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .align(Alignment.Center)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 28.dp
                ),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Entrar",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF202124)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Acesse sua conta para continuar",
                fontSize = 12.sp,
                color = cinzaTexto
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = "Seu e-mail",
                        color = Color(0xFF9AA0A6),
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = {senha = it},
            placeholder = {
                Text(
                    text = "Sua senha",
                    color = Color(0xFF9AA0A6),
                    fontSize = 12.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            visualTransformation = if (mostrarSenha) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                TextButton(
                    onClick = {
                        mostrarSenha = !mostrarSenha
                    }
                ) {
                    Icon(
                        imageVector = if(mostrarSenha){
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = if(mostrarSenha){
                            "Ocultar Senha"
                        } else {
                            "Mostrar Senha"
                        },
                        tint = verdeEscuro
                    )
                }
            }
        )
            Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {
                viewModel.login(
                    email = email,
                    senha = senha
                )
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                enabled = !carregando,
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(containerColor = verdeLeafy)
            )
        {
            if(carregando){
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White
                )
            } else {
                Text(text = "Entrar", fontWeight = FontWeight.Bold)
            }
        }
            if (erro != null) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = erro ?: "",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

        Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFE5E5E5)
                )

                Text(
                    text = "ou",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    color = Color(0xFF999999),
                    fontSize = 12.sp
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFE5E5E5)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
            onClick = {
                // Google será implementado posteriormente
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp), shape = RoundedCornerShape(13.dp),
                colors =  ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF555555)
                )
        ) {
            Text(
                text = "G   Continuar com Google",
                fontSize = 13.sp
            )
        }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Não tem uma conta? ",
                    color = cinzaTexto,
                    fontSize = 12.sp
                )

                TextButton(
                    onClick = onCadastroClick
                ) {
                    Text(
                        text = "Cadastre-se",
                        color = verdeEscuro,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }
}