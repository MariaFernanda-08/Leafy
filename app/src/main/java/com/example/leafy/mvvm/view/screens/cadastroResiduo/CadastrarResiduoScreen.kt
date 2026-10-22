package com.example.leafy.mvvm.view.screens.cadastroResiduo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafy.mvvm.model.repository.SugestaoResiduoRepository

@Composable
fun CadastrarResiduoScreen(
    codigoBarras: String,
    paddingValues: PaddingValues,
    onVoltar: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var instrucoes by remember { mutableStateOf("") }
    var decomposicao by remember { mutableStateOf("") }
    var impacto by remember { mutableStateOf("") }
    var reciclavel by remember { mutableStateOf<Boolean?>(null) }

    val repository = remember { SugestaoResiduoRepository() }

    var enviando by remember { mutableStateOf(false) }
    var enviado by remember { mutableStateOf(false) }
    var mensagemErro by remember { mutableStateOf("") }
    var tentativaEnvio by remember { mutableStateOf(false) }

    // Apenas os campos que já eram obrigatórios continuam sendo validados.
    val codigoValido = codigoBarras.isNotBlank()
    val nomeValido = nome.isNotBlank()
    val tipoValido = tipo.isNotBlank()
    val reciclavelValido = reciclavel != null

    val formularioValido =
        codigoValido &&
                nomeValido &&
                tipoValido &&
                reciclavelValido

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Ajude a cadastrar este produto!",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00A968)
        )

        Text(
            text = "Não encontramos esse código no Leafy. Preencha os campos obrigatórios para sugerir o cadastro."
        )

        OutlinedTextField(
            value = codigoBarras,
            onValueChange = {},
            label = { Text("Código de barras *") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            isError = tentativaEnvio && !codigoValido,
            supportingText = {
                if (tentativaEnvio && !codigoValido) {
                    Text("Código de barras obrigatório.")
                }
            }
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do produto ou material *") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !enviando && !enviado,
            isError = tentativaEnvio && !nomeValido,
            supportingText = {
                if (tentativaEnvio && !nomeValido) {
                    Text("Informe o nome do produto.")
                }
            }
        )

        OutlinedTextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo de resíduo *") },
            placeholder = { Text("Ex.: Plástico, Papel, Vidro") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !enviando && !enviado,
            isError = tentativaEnvio && !tipoValido,
            supportingText = {
                if (tentativaEnvio && !tipoValido) {
                    Text("Informe o tipo de resíduo.")
                }
            }
        )

        Text(
            text = "Este resíduo é reciclável? *",
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { reciclavel = true },
                modifier = Modifier.weight(1f),
                enabled = !enviando && !enviado,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (reciclavel == true) {
                        Color(0xFF00A968)
                    } else {
                        Color.Transparent
                    },
                    contentColor = if (reciclavel == true) {
                        Color.White
                    } else {
                        Color(0xFF00A968)
                    }
                )
            ) {
                Text(if (reciclavel == true) "✓ Sim" else "Sim")
            }

            OutlinedButton(
                onClick = { reciclavel = false },
                modifier = Modifier.weight(1f),
                enabled = !enviando && !enviado,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (reciclavel == false) {
                        Color(0xFFD94848)
                    } else {
                        Color.Transparent
                    },
                    contentColor = if (reciclavel == false) {
                        Color.White
                    } else {
                        Color(0xFFD94848)
                    }
                )
            ) {
                Text(if (reciclavel == false) "✓ Não" else "Não")
            }
        }

        if (tentativaEnvio && !reciclavelValido) {
            Text(
                text = "Selecione Sim ou Não.",
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        OutlinedTextField(
            value = instrucoes,
            onValueChange = { instrucoes = it },
            label = { Text("Como deve ser descartado") },
            placeholder = { Text("Opcional") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            enabled = !enviando && !enviado
        )

        OutlinedTextField(
            value = decomposicao,
            onValueChange = { decomposicao = it },
            label = { Text("Tempo de decomposição") },
            placeholder = { Text("Ex.: Cerca de 450 anos (opcional)") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !enviando && !enviado
        )

        OutlinedTextField(
            value = impacto,
            onValueChange = { impacto = it },
            label = { Text("Impacto ambiental") },
            placeholder = { Text("Opcional") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            enabled = !enviando && !enviado
        )

        Text(
            text = "* Campos obrigatórios",
            fontSize = 12.sp
        )

        Button(
            onClick = {
                tentativaEnvio = true
                mensagemErro = ""

                if (!formularioValido) {
                    mensagemErro = "Preencha os campos obrigatórios."
                    return@Button
                }

                val reciclavelEscolhido = reciclavel ?: return@Button

                enviando = true

                repository.enviarSugestao(
                    codigoBarras = codigoBarras,
                    nome = nome,
                    tipo = tipo,
                    instrucoesDescarte = instrucoes,
                    tempoDecomposicao = decomposicao,
                    impactoAmbiental = impacto,
                    reciclavel = reciclavelEscolhido,
                    onSuccess = {
                        enviando = false
                        enviado = true
                        mensagemErro = ""
                    },
                    onError = { erro ->
                        enviando = false
                        mensagemErro = erro.localizedMessage
                            ?: "Não foi possível enviar a sugestão."
                    }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !enviando && !enviado
        ) {
            Text(
                text = when {
                    enviado -> "Sugestão enviada!"
                    enviando -> "Enviando..."
                    else -> "Enviar sugestão"
                }
            )
        }

        if (mensagemErro.isNotBlank()) {
            Text(
                text = mensagemErro,
                color = Color.Red
            )
        }

        if (enviado) {
            Text(
                text = "Obrigado por contribuir com o Leafy! Sua sugestão foi enviada para análise.",
                color = Color(0xFF00864A)
            )
        }

        Button(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar ao scanner")
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}