package com.example.leafy.mvvm.view.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import com.example.leafy.ui.theme.LeafyBackground
import com.example.leafy.ui.theme.LeafyDarkGreen
import com.example.leafy.ui.theme.LeafyGreen
import com.example.leafy.ui.theme.LeafyWhite

@Composable
fun HomeScreen(
    onConsultaClick: () -> Unit,
    onScannerClick: () -> Unit,
    onMapaClick: () -> Unit,
    onEducacaoClick: () -> Unit,
    onProgressoClick: () -> Unit,
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(LeafyBackground)
        .padding(24.dp), 
        verticalArrangement = Arrangement.Center)
    {
        Text(text = "🌿 Leafy", color = LeafyDarkGreen)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Faça crescer um futuro melhor")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onConsultaClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LeafyGreen,
                contentColor = LeafyWhite
            )
        ) {
            Text(text = ("🔎 Consultar resíduo"))
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onScannerClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LeafyGreen,
                contentColor = LeafyWhite
            )
            ) {
            Text(text = "📷 Escanear produto")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onMapaClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LeafyGreen,
                contentColor = LeafyWhite
            )
        ) {
            Text(text = "📍 Pontos de coleta")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onEducacaoClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LeafyGreen,
                contentColor = LeafyWhite
            )
        ) {
            Text(text = "📚 Educação ambiental")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onProgressoClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LeafyDarkGreen,
                contentColor = LeafyWhite
            )
        ) {
            Text(text = "🏆 Meu progresso")
        }
    }
}