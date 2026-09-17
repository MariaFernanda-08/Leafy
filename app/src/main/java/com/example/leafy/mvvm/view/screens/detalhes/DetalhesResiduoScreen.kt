package com.example.leafy.mvvm.view.screens.detalhes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.leafy.mvvm.model.data.Residuo
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun DetalhesResiduoScreen(residuo: Residuo, onVoltar: () -> Unit){
    val corCategoria = when (residuo.tipo.lowercase()){
        "plástico", "plastico" -> Color(0xFFE57373)
        "papel" -> Color(0xFF64B5F6)
        "vidro" -> Color(0xFF81C784)
        "metal" -> Color(0xFFFFD966)
        "orgânico", "organico" -> Color(0xFFA1887F)
        else -> MaterialTheme.colorScheme.primary
    }

    val corCard = when (residuo.tipo.lowercase()) {
        "plástico", "plastico" -> Color(0xFFFDE7E7)
        "papel" -> Color(0xFFE8F1FB)
        "vidro" -> Color(0xFFE8F5E9)
        "metal" -> Color(0xFFFFF8E1)
        "orgânico", "organico" -> Color(0xFFF3E9E3)
        else -> Color(0xFFEAF8F0)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FBF9))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
        ){
            Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onVoltar) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar"
                )
            }
                Text(
                    text = "Detalhes do resíduo",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = residuo.nome,
            style = MaterialTheme.typography.headlineSmall
        )

        Row(
            modifier = Modifier
                .background(
                    color = corCategoria.copy(alpha = 0.20f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 14.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = residuo.tipo,
                style = MaterialTheme.typography.labelLarge
            )
        }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                containerColor = if (residuo.reciclavel) {
                    Color(0xFFE8F5E9)
                } else {
                    Color(0xFFFBEAEA)
                }
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = if (residuo.reciclavel) {
                    "♻️  Este resíduo é reciclável"
                } else {
                    "❌  Este resíduo não é reciclável"
                },
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        InformacaoCard(
            titulo = "🚮  COMO DESCARTAR",
            texto = residuo.instrucoesDescarte,
            cor = corCard
        )

        InformacaoCard(
            titulo = "⏱️  TEMPO DE DECOMPOSIÇÃO",
            texto = residuo.tempoDecomposicao,
            cor = corCard
        )

        InformacaoCard(
            titulo = "🌎  IMPACTO AMBIENTAL",
            texto = residuo.impactoAmbiental,
            cor = corCard
        )

        if (!residuo.explicacaoReciclagem.isNullOrBlank()){
            InformacaoCard(
                titulo = "⌛ FUTURO DO MATERIAL",
                texto = residuo.explicacaoReciclagem,
                cor = corCard
            )
        }

        if (!residuo.dicaDescarte.isNullOrBlank()){
            InformacaoCard(
                titulo = "🗑️ DICA DE DESCARTE",
                texto = residuo.dicaDescarte,
                cor = corCard
            )
        }

        if (!residuo.curiosidade.isNullOrBlank()){
            InformacaoCard(
                titulo = "🤔 CURIOSIDADE",
                texto = residuo.curiosidade,
                cor = corCard
            )
        }

    }
}

@Composable
private fun InformacaoCard(
    titulo: String,
    texto: String,
    cor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = cor
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = texto,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}