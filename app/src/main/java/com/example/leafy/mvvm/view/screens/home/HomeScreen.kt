package com.example.leafy.mvvm.view.screens.home

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leafy.mvvm.model.data.Usuario
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private data class NivelHome(
    val emoji: String,
    val nome: String,
    val descricao: String,
    val xpMinimo: Int,
    val xpProximo: Int?
)

private val niveisHome = listOf(
    NivelHome(
        "🐜",
        "Formiga Operária",
        "Pequenas ações, grandes resultados.",
        0,
        100
    ),
    NivelHome(
        "🦫",
        "Castor Engenheiro",
        "Construindo soluções sustentáveis.",
        100,
        300
    ),
    NivelHome(
        "🐺",
        "Lobo Guardião",
        "Protetor da alcateia e do território.",
        300,
        600
    ),
    NivelHome(
        "🦅",
        "Condor dos Andes",
        "Visão ampla sobre o planeta.",
        600,
        1000
    ),
    NivelHome(
        "🐢",
        "Tartaruga Milenar",
        "Sabedoria e preservação dos mares.",
        1000,
        1500
    ),
    NivelHome(
        "🐦‍🔥",
        "Fênix",
        "A Terra renascendo das cinzas.",
        1500,
        null
    )
)

fun formatarDataMembro(data: String): String {
    return try {
        val formatos = listOf(
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss"
        )

        val dataConvertida = formatos.firstNotNullOfOrNull { formato ->
            try {
                SimpleDateFormat(
                    formato,
                    Locale.getDefault()
                ).parse(data)
            } catch (e: Exception) {
                null
            }
        }

        val formatoSaida = SimpleDateFormat(
            "MMM\nyyyy",
            Locale("pt", "BR")
        )

        dataConvertida?.let {
            formatoSaida.format(it)
        } ?: "Data desconhecida"

    } catch (e: Exception) {
        "Data desconhecida"
    }
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onConsultaClick: () -> Unit,
    onScannerClick: () -> Unit,
    onMapaClick: () -> Unit,
    onEducacaoClick: () -> Unit,
    onProgressoClick: () -> Unit,
    usuario: Usuario?,
    onSugestoesAdminClick: () -> Unit = {}
) {
    val xpAtual = (usuario?.xp ?: 0).coerceAtLeast(0)

    val nivelAtual = niveisHome.lastOrNull {
        xpAtual >= it.xpMinimo
    } ?: niveisHome.first()

    val indiceNivel = niveisHome.indexOf(nivelAtual)

    val progressoNivel = if (nivelAtual.xpProximo != null) {
        (
                (xpAtual - nivelAtual.xpMinimo).toFloat() /
                        (nivelAtual.xpProximo - nivelAtual.xpMinimo)
                ).coerceIn(0f, 1f)
    } else {
        1f
    }

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
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF08BD73)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    // PERFIL DO USUÁRIO
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
                                text = usuario?.nome ?: "Usuário Leafy",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Membro desde\n" +
                                        (usuario?.criadoEm?.let {
                                            formatarDataMembro(it)
                                        } ?: "Data desconhecida"),
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Button(
                            onClick = onProgressoClick,
                            contentPadding = PaddingValues(
                                horizontal = 12.dp,
                                vertical = 8.dp
                            ),
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

                    // NÍVEL E XP REAIS
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
                                    text = nivelAtual.emoji,
                                    style = MaterialTheme.typography.headlineMedium
                                )

                                Spacer(modifier = Modifier.size(12.dp))

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = nivelAtual.nome,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Text(
                                        text = "Nível ${indiceNivel + 1}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = "$xpAtual",
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
                                        text = nivelAtual.xpProximo?.let {
                                            "de $it XP"
                                        } ?: "Nível máximo",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            LinearProgressIndicator(
                                progress = { progressoNivel },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp),
                                color = Color(0xFFFFC107),
                                trackColor = Color(0xFF087F50)
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = if (nivelAtual.xpProximo != null) {
                                    "${(progressoNivel * 100).toInt()}% do nível atual"
                                } else {
                                    "Você alcançou o nível máximo!"
                                },
                                modifier = Modifier.align(Alignment.End),
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // INDICADORES REAIS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        EstatisticaCard(
                            valor = "$xpAtual",
                            texto = "XP total",
                            modifier = Modifier.weight(1f)
                        )

                        EstatisticaCard(
                            valor = "${indiceNivel + 1}/6",
                            texto = "Nível",
                            modifier = Modifier.weight(1f)
                        )

                        EstatisticaCard(
                            valor = "${(progressoNivel * 100).toInt()}%",
                            texto = "Progresso",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // ATALHO EXCLUSIVO DO ADMINISTRADOR
        if (usuario?.tipoUsuario == "admin") {
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = "Área Administrativa",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF17201B)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AcaoRapidaCard(
                        titulo = "Sugestões dos usuários",
                        descricao = "Analise e aprove novos resíduos cadastrados",
                        icon = Icons.Default.Eco,
                        cor = Color(0xFFDDF7E8),
                        onClick = onSugestoesAdminClick
                    )
                }
            }
        }

        // AÇÕES RÁPIDAS
        item {
            Column(
                modifier = Modifier.padding(horizontal = 4.dp)
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
                    descricao = "Identifique o resíduo pelo código de barras",
                    icon = Icons.Default.CameraAlt,
                    cor = Color(0xFFE0EBFF),
                    onClick = onScannerClick
                )

                Spacer(modifier = Modifier.height(10.dp))

                AcaoRapidaCard(
                    titulo = "Pontos de Coleta",
                    descricao = "Encontre locais de descarte",
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
    icon: ImageVector,
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
        border = BorderStroke(1.dp, cor)
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