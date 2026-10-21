package com.example.leafy.mvvm.view.screens.consulta

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.leafy.mvvm.viewmodel.ConsultaViewModel
import com.example.leafy.mvvm.model.data.Residuo

@Composable
fun ConsultaScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: ConsultaViewModel = viewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        item {
            Text(
                text = "Encontre um resíduo",
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF17211C)
            )

            Text(
                text = "Pesquise pelo nome do produto ou material para descobrir como descartá-lo corretamente.",
                modifier = Modifier.padding(
                    top = 6.dp,
                    bottom = 18.dp
                ),
                fontSize = 14.sp,
                color = Color(0xFF4F5B55)
            )

            OutlinedTextField(
                value = viewModel.pesquisa,
                onValueChange = {
                    viewModel.atualizarPesquisa(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                label = {
                    Text("Pesquisar resíduo")
                },
                placeholder = {
                    Text("Ex.: Garrafa PET")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pesquisar"
                    )
                },
                singleLine = true,
                shape = MaterialTheme.shapes.large
            )

            Text(
                text = "Explore por categoria",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF17211C)
            )

            CategoriaCard(
                emoji = "♻️",
                nome = "Plástico",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                selecionado = viewModel.categoriaSelecionada == "Plástico",
                onClick = {
                    viewModel.filtrarPorCategoria("Plástico")
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CategoriaCard(
                    emoji = "📄",
                    nome = "Papel",
                    modifier = Modifier.weight(1f),
                    selecionado = viewModel.categoriaSelecionada == "Papel",
                    onClick = {
                        viewModel.filtrarPorCategoria("Papel")
                    }
                )

                CategoriaCard(
                    emoji = "🍾",
                    nome = "Vidro",
                    modifier = Modifier.weight(1f),
                    selecionado = viewModel.categoriaSelecionada == "Vidro",
                    onClick = {
                        viewModel.filtrarPorCategoria("Vidro")
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CategoriaCard(
                    emoji = "🔩",
                    nome = "Metal",
                    modifier = Modifier.weight(1f),
                    selecionado = viewModel.categoriaSelecionada == "Metal",
                    onClick = {
                        viewModel.filtrarPorCategoria("Metal")
                    }
                )

                CategoriaCard(
                    emoji = "🌱",
                    nome = "Orgânico",
                    modifier = Modifier.weight(1f),
                    selecionado = viewModel.categoriaSelecionada == "Orgânico",
                    onClick = {
                        viewModel.filtrarPorCategoria("Orgânico")
                    }
                )
            }

            if (viewModel.categoriaSelecionada != null) {
                Text(
                    text = "Ver todos os resíduos",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 0.dp,
                            bottom = 12.dp
                        )
                        .clickable {
                            viewModel.limparFiltro()
                        },
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            if (
                viewModel.pesquisa.isNotBlank() &&
                viewModel.resultados.isEmpty()
            ) {
                Text(
                    text = "😕 Nenhum resíduo encontrado.",
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (
            viewModel.pesquisa.isNotBlank() &&
            viewModel.resultados.isNotEmpty()
        ) {
            item {
                Text(
                    text = "Resultado da busca",
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 12.dp
                    ),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF17211C)
                )
            }
            
            items(viewModel.resultados) { residuo ->
                ResiduoCard(
                    residuo = residuo,
                    onClick = {
                        navController.navigate(
                            "detalhes/${residuo.id}"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun CategoriaCard(
    emoji: String,
    nome: String,
    modifier: Modifier = Modifier,
    selecionado: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor = if (selecionado) {
        when (nome) {
            "Plástico" -> Color(0xFFE57373)
            "Papel" -> Color(0xFF64B5F6)
            "Vidro" -> Color(0xFF81C784)
            "Metal" -> Color(0xFFFFD966)
            "Orgânico" -> Color(0xFFA1887F)
            else -> MaterialTheme.colorScheme.primary
        }
    } else {
        Color(0xFFDCE5DE)
    }

    val textColor = if (selecionado) {
        if (nome == "Metal") {
            Color.Black
        } else {
            Color.White
        }
    } else {
        Color(0xFF263238)
    }

    androidx.compose.material3.Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = emoji,
                fontSize = 28.sp
            )

            Text(
                text = nome,
                modifier = Modifier.padding(top = 6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Composable
fun ResiduoCard(
    residuo: Residuo,
    onClick: () -> Unit
) {
    val backgroundColor = when (
        residuo.tipo.lowercase().trim()
    ) {
        "plástico", "plastico" -> Color(0xFFFFE1E1)
        "papel" -> Color(0xFFE0EDFF)
        "vidro" -> Color(0xFFE1F3E2)
        "metal" -> Color(0xFFFFF4CC)
        "orgânico", "organico" -> Color(0xFFF0E3DC)
        else -> MaterialTheme.colorScheme.surface
    }

    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onClick() },
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = residuo.nome,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF263238)
            )

            Text(
                text = residuo.tipo,
                modifier = Modifier.padding(top = 4.dp),
                fontSize = 13.sp,
                color = Color(0xFF607D8B)
            )

            if (residuo.instrucoesDescarte.isNotBlank()) {
                Text(
                    text = residuo.instrucoesDescarte,
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    color = Color(0xFF263238)
                )
            }
        }
    }
}
