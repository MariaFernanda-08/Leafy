package com.example.leafy.mvvm.view.screens.detalhes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.leafy.mvvm.model.data.Residuo

@Composable
fun DetalhesResiduoScreen(residuo: Residuo){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)){
        Text(text = residuo.nome, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Tipo: ${residuo.tipo}", style = MaterialTheme.typography.titleMedium)
        Text(
            text = if (residuo.reciclavel) {
                "♻️ Este resíduo é reciclável"
            } else {
                "Este resíduo não é reciclável"
            }
        )
        Text(text = "COMO DESCARTAR", style = MaterialTheme.typography.titleMedium)
        Text(text = residuo.instrucoesDescarte)
        Text(text = "TEMPO DE DECOMPOSIÇÃO", style = MaterialTheme.typography.titleMedium)
        Text(text = residuo.tempoDecomposicao)
        Text(text = "IMPACTO AMBIENTAL", style = MaterialTheme.typography.titleMedium)
        Text(text = residuo.impactoAmbiental)
    }
}