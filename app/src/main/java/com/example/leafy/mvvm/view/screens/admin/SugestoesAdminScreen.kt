package com.example.leafy.mvvm.view.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.viewmodel.SugestoesAdminViewModel

@Composable
fun SugestoesAdminScreen(
    paddingValues: PaddingValues,
    onVoltar: () -> Unit,
    viewModel: SugestoesAdminViewModel = viewModel()
) {
    val sugestoes by viewModel.sugestoes.collectAsState()
    val carregando by viewModel.carregando.collectAsState()
    val mensagem by viewModel.mensagem.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarPendentes()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9F6))
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Text(
            text = "Sugestões dos usuários",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF17201B)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Analise os resíduos sugeridos antes de adicioná-los ao catálogo.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF59645D)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (mensagem != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFDDF7E8)
                )
            ) {
                Text(
                    text = mensagem.orEmpty(),
                    modifier = Modifier.padding(12.dp),
                    color = Color(0xFF176B43)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        when {
            carregando && sugestoes.isEmpty() -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFF08BD73)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Carregando sugestões...")
                }
            }

            sugestoes.isEmpty() -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🌱",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Nenhuma sugestão pendente",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = { viewModel.carregarPendentes() }
                    ) {
                        Text("Atualizar")
                    }
                }
            }

            else -> {
                Text(
                    text = "${sugestoes.size} sugestão(ões) aguardando análise",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF168B57)
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = sugestoes,
                        key = { it.id }
                    ) { sugestao ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = sugestao.nome,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF17201B)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Código de barras: ${sugestao.codigoBarras}")
                                Text("Tipo: ${sugestao.tipo}")

                                Text(
                                    text = if (sugestao.reciclavel) {
                                        "Reciclável: Sim"
                                    } else {
                                        "Reciclável: Não"
                                    },
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (sugestao.reciclavel) {
                                        Color(0xFF168B57)
                                    } else {
                                        Color(0xFFB45309)
                                    }
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Instruções de descarte",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(sugestao.instrucoesDescarte)

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Tempo de decomposição",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(sugestao.tempoDecomposicao)

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Impacto ambiental",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(sugestao.impactoAmbiental)

                                Spacer(modifier = Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            viewModel.aprovar(sugestao)
                                        },
                                        enabled = !carregando,
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF08BD73)
                                        )
                                    ) {
                                        Text("Aprovar")
                                    }

                                    OutlinedButton(
                                        onClick = {
                                            viewModel.rejeitar(sugestao)
                                        },
                                        enabled = !carregando,
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text("Rejeitar")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar para o início")
        }
    }
}