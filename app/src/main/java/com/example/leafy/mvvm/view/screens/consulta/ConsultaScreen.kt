package com.example.leafy.mvvm.view.screens.consulta

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.viewmodel.ConsultaViewModel
import androidx.compose.foundation.clickable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color


@Composable
fun ConsultaScreen(navController: NavController, viewModel: ConsultaViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        Text(
            text = "Encontre um resíduo",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Pesquise pelo nome do produto ou material para descobrir como descartá-lo corretamente.",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = viewModel.pesquisa,
            onValueChange = { viewModel.atualizarPesquisa(it) },
            modifier = Modifier.fillMaxWidth(),
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
            style = MaterialTheme.typography.titleMedium
        )

        CategoriaCard(
            emoji = "♻️",
            nome = "Plástico",
            modifier = Modifier.fillMaxWidth(),
            selecionado = viewModel.categoriaSelecionada == "Plástico",
            onClick = { viewModel.filtrarPorCategoria("Plástico")}
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CategoriaCard(
                emoji = "📄",
                nome = "Papel",
                modifier = Modifier.weight(1f),
                selecionado = viewModel.categoriaSelecionada == "Papel",
                onClick = { viewModel.filtrarPorCategoria("Papel")}
            )

            CategoriaCard(
                emoji = "🍾",
                nome = "Vidro",
                modifier = Modifier.weight(1f),
                selecionado = viewModel.categoriaSelecionada == "Vidro",
                onClick = { viewModel.filtrarPorCategoria("Vidro")}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CategoriaCard(
                emoji = "🔩",
                nome = "Metal",
                modifier = Modifier.weight(1f),
                selecionado = viewModel.categoriaSelecionada == "Metal",
                onClick = { viewModel.filtrarPorCategoria("Metal")}
            )

            CategoriaCard(
                emoji = "🌱",
                nome = "Orgânico",
                modifier = Modifier.weight(1f),
                selecionado = viewModel.categoriaSelecionada == "Orgânico",
                onClick = { viewModel.filtrarPorCategoria("Orgânico")}
            )
        }

        if (viewModel.categoriaSelecionada != null){
            Text(
                text = "Ver todos os resíduos",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.limparFiltro()
                    },
                style = MaterialTheme.typography.labelLarge
            )
        }

        if (viewModel.pesquisa.isBlank()) {

        } else if (viewModel.resultados.isEmpty()) {

            Text(
                text = "😕 Nenhum resíduo encontrado.",
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
               items(viewModel.resultados) {residuo ->
                   ResiduoCard(
                       residuo = residuo,
                       onClick = {
                           navController.navigate("detalhes/${residuo.id}")
                       }
                   )
               }
            }
        }
    }
}
@Composable
private fun ResiduoCard(
    residuo: Residuo,
    onClick: () -> Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = when(residuo.tipo.lowercase()){
                "plástico","plastico"  -> Color(0xFFFFE1E1)

                "papel" -> Color(0xFFE0EDFF)

                "vidro" -> Color(0xFFE1F3E2)

                "metal" -> Color(0xFFFFF4CC)

                "orgânico","organico"  -> Color(0xFFF0E3DC)

                else -> MaterialTheme.colorScheme.surface
            }
        )
        )
    {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            Text(text = residuo.nome, style = MaterialTheme.typography.titleMedium)

            Text(text = "Tipo: ${residuo.tipo}", style = MaterialTheme.typography.bodyMedium)

            Text(text = if (residuo.reciclavel) {
                    "♻️ Reciclável"
                } else {
                    "❌ Não reciclável"
                },
                style = MaterialTheme.typography.bodyMedium
            )

            Text(text = "Toque para ver detalhes →", style = MaterialTheme.typography.labelMedium)
        }
    }
}
@Composable
private fun CategoriaCard(
    emoji: String,
    nome: String,
    modifier: Modifier = Modifier,
    selecionado: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if(selecionado){
                when (nome) {
                    "Plástico" -> Color(0xFFE57373)
                    "Papel" -> Color(0xFF64B5F6)
                    "Vidro" -> Color(0xFF81C784)
                    "Metal" -> Color(0xFFFFD966)
                    "Orgânico" -> Color(0xFFA1887F)
                    else -> MaterialTheme.colorScheme.primary
                }
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = nome,
                style = MaterialTheme.typography.labelLarge,
                color = if (selecionado) {
                    if (nome == "Metal") Color.Black else Color.White
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}