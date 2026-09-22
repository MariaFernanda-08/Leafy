package com.example.leafy.mvvm.view.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leafy.R

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onConsultaClick: () -> Unit,
    onScannerClick: () -> Unit,
    onMapaClick: () -> Unit,
    onEducacaoClick: () -> Unit,
    onProgressoClick: () -> Unit,
){

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F9F6))
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF08BD73)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Card(
                                modifier = Modifier.size(58.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF4DD99B)
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Usuário",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(13.dp),
                                    tint = Color(0xFF5A2D82)
                                )
                            }

                            Spacer(modifier = Modifier.size(14.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = "Usuário\nLeafy",
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Membro desde Fev\n2025",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Button(
                                onClick = onProgressoClick,
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF20C783),
                                    contentColor = Color.White
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 0.dp
                                )
                            ) {
                                Text(
                                    text = "Ver Tudo",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF20C783)
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = "🦫",
                                        style = MaterialTheme.typography.headlineMedium
                                    )

                                    Spacer(modifier = Modifier.size(12.dp))

                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {

                                        Text(
                                            text = "Castor",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            text = "Engenheiro",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            text = "Nível 2",
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }

                                    Column(
                                        horizontalAlignment = Alignment.End
                                    ) {

                                        Text(
                                            text = "245",
                                            color = Color.White,
                                            style = MaterialTheme.typography.headlineSmall,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            text = "XP",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            text = "de 300 XP",
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(10.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF087F50)
                                    )
                                ) {

                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth(0.82f)
                                            .fillMaxSize(),
                                        shape = RoundedCornerShape(10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(0xFFFFC107)
                                        )
                                    ) {}
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            EstatisticaCard(
                                valor = "5",
                                texto = "Conquistas",
                                modifier = Modifier.weight(1f)
                            )

                            EstatisticaCard(
                                valor = "7",
                                texto = "Dias Seguidos",
                                modifier = Modifier.weight(1f)
                            )

                            EstatisticaCard(
                                valor = "12",
                                texto = "Contribuições",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            item {

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {

                    Text(
                        text = "Ações Rápidas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF17201B)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AcaoRapidaCard(
                        titulo = "Consultar Resíduo",
                        descricao = "Descubra como descartar corretamente",
                        icon = Icons.Default.Eco,
                        cor = Color(0xFFDDF7E8),
                        onClick = onConsultaClick
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AcaoRapidaCard(
                        titulo = "Escanear Símbolo de Reciclagem",
                        descricao = "Identifique o tipo de plástico pelo triângulo",
                        icon = Icons.Default.CameraAlt,
                        cor = Color(0xFFE0EBFF),
                        onClick = onScannerClick
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AcaoRapidaCard(
                        titulo = "Pontos de Coleta",
                        descricao = "Encontre locais próximos a você",
                        icon = Icons.Default.LocationOn,
                        cor = Color(0xFFF0E3FF),
                        onClick = onMapaClick
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AcaoRapidaCard(
                        titulo = "Educação Ambiental",
                        descricao = "Aprenda mais sobre sustentabilidade",
                        icon = Icons.Default.MenuBook,
                        cor = Color(0xFFFFF1D6),
                        onClick = onEducacaoClick
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
}

@Composable
private fun EstatisticaCard(
    valor: String,
    texto: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.height(82.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF20C783)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = valor,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = texto,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun AcaoRapidaCard(
    titulo: String,
    descricao: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    cor: Color,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            cor
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.size(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cor
                )
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    tint = Color(0xFF168B57)
                )
            }

            Spacer(modifier = Modifier.size(14.dp))

            Column {

                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}