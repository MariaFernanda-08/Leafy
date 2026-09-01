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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.model.data.Residuo
import com.example.leafy.mvvm.viewmodel.ConsultaViewModel
import androidx.compose.foundation.clickable
import androidx.navigation.NavController

@Composable
fun ConsultaScreen(navController: NavController, viewModel: ConsultaViewModel = viewModel()) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp))
    {
        Text(
            text = "Consulta de Resíduos",
            style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = viewModel.pesquisa,
            onValueChange = {viewModel.atualizarPesquisa(it)},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Pesquisar resíduo")},
            placeholder = {
                Text("Ex.: Garrafa PET")},
            singleLine = true
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.resultados){ residuo -> ResiduoCard(
                residuo = residuo,
                onClick = {navController.navigate("detalhes/${residuo.id}")})}
        }
    }
}

@Composable
private fun ResiduoCard(
    residuo: Residuo,
    onClick: () -> Unit
){
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() })
    {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            Text(text = residuo.nome, style = MaterialTheme.typography.titleMedium)
            Text(text = "Tipo: ${residuo.tipo}")
            Text(
                text = if (residuo.reciclavel) {
                    "♻️ Reciclável"
                } else {
                    "Não Reciclável"
                }
            )
            Text(text = "Descarte: ${residuo.instrucoesDescarte}")
            Text(text = "Decomposição: ${residuo.tempoDecomposicao}")
            Text(text = "Impacto Ambiental: ${residuo.impactoAmbiental}")
        }
    }
}