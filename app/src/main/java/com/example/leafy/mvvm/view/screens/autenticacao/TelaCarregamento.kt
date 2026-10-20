package com.example.leafy.mvvm.view.screens.autenticacao

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.leafy.R

@Composable
fun TelaCarregamento() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00C66B)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.leafy_logo),
            contentDescription = "Logo do Leafy",
            modifier = Modifier.size(180.dp)
        )

        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 3.dp
        )
    }
}