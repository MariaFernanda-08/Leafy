package com.example.leafy.mvvm.view.screens.autenticacao

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.viewmodel.UsuarioViewModel

@Composable
fun CadastroScreen(
    onCadastroSucesso: () -> Unit,
    onVoltarLogin: () -> Unit,
    viewModel: UsuarioViewModel = viewModel()
){
    var nome by rememberSaveable { mutableStateOf("")}
    var email by rememberSaveable { mutableStateOf("")}
    var senha by rememberSaveable { mutableStateOf("")}
    var confirmarSenha by rememberSaveable { mutableStateOf("") }

    var mostrarSenha by rememberSaveable { mutableStateOf(false) }
    var mostrarConfirmarSenha by rememberSaveable { mutableStateOf(false) }

    var aceitouTermos by rememberSaveable { mutableStateOf(false) }
    
    val carregando by viewModel.carregando.collectAsState()
    val erro by viewModel.erro.collectAsState()
    val sucesso by viewModel.sucesso.collectAsState()
    
    LaunchedEffect(sucesso) {
        if (sucesso != null){
            onCadastroSucesso()
            viewModel.limparMensagens()
        }
    }

    val verdeLeafy = Color(0xFF00C66B)
    val verdeEscuro = Color(0xFF00A968)
    val cinzaTexto = Color(0xFF8A8F98)
    val cinzaEscuro = Color(0xFF202124)

    val formularioValido =
        nome.isNotBlank() &&
                email.isNotBlank() &&
                senha.length >= 6 &&
                confirmarSenha.isNotBlank() &&
                senha == confirmarSenha &&
                aceitouTermos

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(verdeLeafy)
    ) {
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 32.dp,
                top = 48.dp,
                end = 24.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = Color(0xFF42D78F),
                        shape = CircleShape
                    )
                    .clickable {
                        onVoltarLogin()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Column {
                Text(
                    text = "Criar conta",
                    color = Color.White,
                    fontSize = 11.sp
                )

                Text(
                    text = "Junte-se ao Leafy",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .align(Alignment.Center)
                .padding(top = 0.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                ),
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF0FFF7),
                        shape = RoundedCornerShape(13.dp)
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 12.dp
                    )
            ) {
                Text(
                    text = "✦   Ganhe 500 XP de boas-vindas ao criar sua conta!",
                    color = verdeEscuro,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

        
        OutlinedTextField(
            value = nome, 
            onValueChange = {nome = it},
            placeholder = {
                Text(
                    text = "Seu nome completo",
                    color = cinzaTexto,
                    fontSize = 12.sp
                )
            },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Nome",
                        tint = cinzaTexto
                    )

            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedTextField(
            value = email, 
            onValueChange = {email = it},
            placeholder = {
                Text(
                    text = "Seu e-mail",
                    color = cinzaTexto,
                    fontSize = 12.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "E-mail",
                    tint = cinzaTexto
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
                    text = "Crie uma senha (mín. 6 caracteres)",
                    color = cinzaTexto,
                    fontSize = 12.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Senha",
                    tint = cinzaTexto
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        mostrarSenha = !mostrarSenha
                    }
                ) {
                    Icon(
                        imageVector = if (mostrarSenha) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },
                        contentDescription = if (mostrarSenha) {
                            "Ocultar senha"
                        } else {
                            "Mostrar senha"
                        },
                        tint = verdeEscuro
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            visualTransformation = if (mostrarSenha) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmarSenha,
                onValueChange = { confirmarSenha = it },
                placeholder = {
                    Text(
                        text = "Confirme a senha",
                        color = cinzaTexto,
                        fontSize = 12.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirmar senha",
                        tint = cinzaTexto
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            mostrarConfirmarSenha = !mostrarConfirmarSenha
                        }
                    ) {
                        Icon(
                            imageVector = if (mostrarConfirmarSenha) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (mostrarConfirmarSenha) {
                                "Ocultar senha"
                            } else {
                                "Mostrar senha"
                            },
                            tint = verdeEscuro
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                visualTransformation = if (mostrarConfirmarSenha) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        aceitouTermos = !aceitouTermos
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = aceitouTermos,
                    onCheckedChange = {
                        aceitouTermos = it
                    }
                )

                Text(
                    text = "Li e concordo com os Termos de Uso e a Política de Privacidade.",
                    color = cinzaTexto,
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
            onClick = {
                viewModel.cadastrar(
                    nome = nome,
                    email = email,
                    senha = senha
                )
            },
            modifier = Modifier.fillMaxWidth().fillMaxWidth().height(46.dp),
            enabled = !carregando,
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = verdeLeafy,
                disabledContainerColor = Color(0xFFB8E8D1)
            )
        ) {
            if (carregando){
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White
                )
            } else {
                Text("Criar conta",
                    fontWeight = FontWeight.Bold)
            }
        }
            Spacer(modifier = Modifier.height(14.dp))


            if (erro != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = erro ?: "",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                    )
            }
        }
    }
}