package com.example.leafy.mvvm.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.leafy.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.IconButton

@Composable
fun LeafyLayout(
    rotaAtual: String,
    onHomeClick: () -> Unit,
    onConsultaClick: () -> Unit,
    onScannerClick: () -> Unit,
    onMapaClick: () -> Unit,
    onEducacaoClick: () -> Unit,
    mostrarSair: Boolean = false,
    onSairClick: () -> Unit = {},
    conteudo: @Composable (PaddingValues) -> Unit
) {

    Scaffold(
        containerColor = Color(0xFFF4F9F6),

        topBar = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF00A65A))
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
            ) {

                androidx.compose.foundation.layout.Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Card(
                        modifier = Modifier.size(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF16B86A)
                        )
                    ) {

                        Image(
                            painter = painterResource(
                                id = R.drawable.leafy_logo
                            ),
                            contentDescription = "Logo Leafy",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(3.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    androidx.compose.foundation.layout.Spacer(
                        modifier = Modifier.size(14.dp)
                    )

                    Column {

                        Text(
                            text = "Leafy",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Faça crescer um futuro melhor!",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    if (mostrarSair){
                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = onSairClick,
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    color = Color(0xFF16B86A),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Logout,
                                contentDescription = "Sair",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        },

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                val coresNavegacao = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF3D8B68),
                    selectedTextColor = Color(0xFF3D8B68),
                    indicatorColor = Color(0xFFE1F1E8)
                )

                NavigationBarItem(
                    selected = rotaAtual == "home",
                    onClick = onHomeClick,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Início"
                        )
                    },
                    label = {
                        Text("Início")
                    },
                    colors = coresNavegacao
                )

                NavigationBarItem(
                    selected = rotaAtual == "consulta",
                    onClick = onConsultaClick,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Consultar"
                        )
                    },
                    label = {
                        Text("Consultar")
                    },
                    colors = coresNavegacao
                )

                NavigationBarItem(
                    selected = rotaAtual == "scanner",
                    onClick = onScannerClick,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "Scanner"
                        )
                    },
                    label = {
                        Text("Scanner")
                    },
                    colors = coresNavegacao
                )

                NavigationBarItem(
                    selected = rotaAtual == "mapa",
                    onClick = onMapaClick,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Pontos"
                        )
                    },
                    label = {
                        Text("Pontos")
                    },
                    colors = coresNavegacao
                )

                NavigationBarItem(
                    selected = rotaAtual == "educacao",
                    onClick = onEducacaoClick,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = "Educar"
                        )
                    },
                    label = {
                        Text("Educar")
                    },
                    colors = coresNavegacao
                )
            }
        }
    ) { paddingValues ->

        conteudo(paddingValues)
    }
}