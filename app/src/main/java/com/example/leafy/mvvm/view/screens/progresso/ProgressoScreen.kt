package com.example.leafy.mvvm.view.screens.progresso

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.viewmodel.UsuarioViewModel

@Composable
fun ProgressoScreen(
    paddingValues: PaddingValues
) {
    val usuarioViewModel: UsuarioViewModel = viewModel()

    val usuario by usuarioViewModel.usuario.collectAsState()
    val sequenciaDias by usuarioViewModel.sequenciaDias.collectAsState()
    val sugestoesEnviadas by usuarioViewModel.sugestoesEnviadas.collectAsState()
    val consultasRealizadas by usuarioViewModel.consultasRealizadas.collectAsState()

    LaunchedEffect(Unit) {
        usuarioViewModel.carregarUsuarioAtual()
    }

    val xpAtual = usuario?.xp ?: 0

    val nivelAtual = niveisLeafy.lastOrNull {
        xpAtual >= it.xpMinimo
    } ?: niveisLeafy.first()

    val proximoNivel = niveisLeafy.firstOrNull {
        it.xpMinimo > xpAtual
    }

    val progressoNivel = if (proximoNivel != null) {
        (
                (xpAtual - nivelAtual.xpMinimo).toFloat() /
                        (proximoNivel.xpMinimo - nivelAtual.xpMinimo)
                ).coerceIn(0f, 1f)
    } else {
        1f
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9F6))
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Meu Progresso",
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF17211C)
        )

        Text(
            text = "Continue cuidando do planeta!🌍",
            modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
            fontSize = 14.sp,
            color = Color(0xFF496057)
        )

        // PROGRESSO DO USUÁRIO
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF7EEFF)
            ),
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nivelAtual.emoji,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Column(
                        modifier = Modifier.padding(start = 14.dp)
                    ) {
                        Text(
                            text = nivelAtual.nome,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF64159C)
                        )

                        Text(
                            text = nivelAtual.descricao,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF8B20C7)
                        )

                        Text(
                            text = "Nível ${niveisLeafy.indexOf(nivelAtual) + 1}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF8B20C7)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$xpAtual XP",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFF64159C)
                    )

                    Text(
                        text = if (proximoNivel == null) {
                            "Nível máximo!"
                        } else {
                            "Próximo: ${proximoNivel.nome} (${proximoNivel.xpMinimo} XP)"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF8B20C7)
                    )
                }

                LinearProgressIndicator(
                    progress = { progressoNivel },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = Color(0xFFB21BD6),
                    trackColor = Color(0xFFE8D1F5)
                )

                Text(
                    text = "${(progressoNivel * 100).toInt()}%",
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF64159C)
                )
            }
        }

        // SEQUÊNCIA DIÁRIA
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF5EC)
            ),
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "📈",
                    style = MaterialTheme.typography.headlineMedium
                )

                Column(
                    modifier = Modifier.padding(start = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Sequência Diária 🔥",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF9B3517)
                    )

                    Text(
                        text = if (sequenciaDias == 1) {
                            "1 dia consecutivo usando o app!"
                        } else {
                            "$sequenciaDias dias consecutivos usando o app!"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFB84A25)
                    )

                    Text(
                        text = "Continue assim para ganhar +20 XP por dia",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB84A25)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // GANHE XP
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F1FC)
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "💎 Ganhe XP",
                    style = MaterialTheme.typography.titleLarge
                )

                HorizontalDivider(
                    color = Color(0xFFD8E5DF),
                    thickness = 1.dp
                )

                AcaoXpCard(
                    emoji = "☆",
                    titulo = "Cadastrar produtos",
                    descricao = "Sugira novos produtos",
                    xp = "+5 XP",
                    cor = Color(0xFFDCEAFF)
                )

                AcaoXpCard(
                    emoji = "♧",
                    titulo = "Procurar produtos",
                    descricao = "Consulte informações sobre resíduos",
                    xp = "+10 XP",
                    cor = Color(0xFFDDF3E7)
                )

                AcaoXpCard(
                    emoji = "🏆",
                    titulo = "Confirmar Informações",
                    descricao = "Sugestão aprovada pelo administrador",
                    xp = "+5 XP",
                    cor = Color(0xFFF0DEFF)
                )

                AcaoXpCard(
                    emoji = "📈",
                    titulo = "Usar Diariamente",
                    descricao = "Mantenha sua participação ativa",
                    xp = "+20 XP",
                    cor = Color(0xFFFFF0C7)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // NÍVEIS
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F4EE)
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "🏆 Níveis",
                    style = MaterialTheme.typography.titleLarge
                )

                HorizontalDivider(
                    color = Color(0xFFD8E5DF),
                    thickness = 1.dp
                )

                niveisLeafy.forEach { nivel ->
                    val faixaXp = if (nivel.xpProximo != null) {
                        "${nivel.xpMinimo} - ${nivel.xpProximo - 1} XP"
                    } else {
                        "${nivel.xpMinimo}+ XP"
                    }

                    // O nível atual é identificado exclusivamente pelo XP.
                    val eNivelAtual =
                        xpAtual >= nivel.xpMinimo &&
                                (nivel.xpProximo == null || xpAtual < nivel.xpProximo)

                    // Um nível só fica concluído quando o usuário já alcançou o seguinte.
                    val jaAlcancado =
                        nivel.xpProximo != null &&
                                xpAtual >= nivel.xpProximo

                    NivelCard(
                        emoji = nivel.emoji,
                        nome = nivel.nome,
                        descricao = nivel.descricao,
                        xp = when {
                            eNivelAtual -> "$faixaXp • NÍVEL ATUAL"
                            jaAlcancado -> "$faixaXp • CONCLUÍDO"
                            else -> faixaXp
                        },
                        atual = eNivelAtual
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // CONQUISTAS
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF3D6)
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "🏅 Conquistas",
                    style = MaterialTheme.typography.titleLarge
                )

                HorizontalDivider(
                    color = Color(0xFFE5DCC5),
                    thickness = 1.dp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ConquistaCard(
                        emoji = "🌱",
                        titulo = "Primeiro Passo",
                        descricao = "Tenha 1 sugestão aprovada",
                        progresso = "${sugestoesEnviadas.coerceAtMost(1)}/1 aprovada",
                        desbloqueada = sugestoesEnviadas >= 1,
                        modifier = Modifier.weight(1f)
                    )

                    ConquistaCard(
                        emoji = "🗺️",
                        titulo = "Explorador",
                        descricao = "Tenha 5 sugestões aprovadas",
                        progresso = "${sugestoesEnviadas.coerceAtMost(5)}/5 aprovadas",
                        desbloqueada = sugestoesEnviadas >= 5,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ConquistaCard(
                        emoji = "🔒",
                        titulo = "Ajudante",
                        descricao = "Tenha 10 sugestões aprovadas",
                        progresso = "${sugestoesEnviadas.coerceAtMost(10)}/10 aprovadas",
                        desbloqueada = sugestoesEnviadas >= 10,
                        modifier = Modifier.weight(1f)
                    )

                    ConquistaCard(
                        emoji = "🔥",
                        titulo = "Persistente",
                        descricao = "Use o app por 7 dias seguidos",
                        progresso = "${sequenciaDias.coerceAtMost(7)}/7 dias",
                        desbloqueada = sequenciaDias >= 7,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ConquistaCard(
                        emoji = "🛡️",
                        titulo = "Guardião Verde",
                        descricao = "Atinja 100 XP",
                        progresso = "${xpAtual.coerceAtMost(100)}/100 XP",
                        desbloqueada = xpAtual >= 100,
                        modifier = Modifier.weight(1f)
                    )

                    ConquistaCard(
                        emoji = "♻️",
                        titulo = "Reciclador Mestre",
                        descricao = "Consulte 50 produtos",
                        progresso = "${consultasRealizadas.coerceAtMost(50)}/50 consultas",
                        desbloqueada = consultasRealizadas >= 50,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun AcaoXpCard(
    emoji: String,
    titulo: String,
    descricao: String,
    xp: String,
    cor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = cor
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.headlineMedium
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = xp,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun NivelCard(
    emoji: String,
    nome: String,
    descricao: String,
    xp: String,
    atual: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (atual) {
                Color(0xFFE1C4FF)
            } else {
                Color(0xFFF0F5F3)
            }
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.headlineMedium
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nome,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF352044)
                    )

                    if (atual) {
                        Text(
                            text = " ATUAL",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF7B1FA2),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF493B52)
                )

                Text(
                    text = xp,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (atual) {
                        Color(0xFF7B1FA2)
                    } else {
                        Color(0xFF66736D)
                    },
                    fontWeight = if (atual) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    }
                )
            }
        }
    }
}

@Composable
private fun ConquistaCard(
    emoji: String,
    titulo: String,
    descricao: String,
    progresso: String,
    desbloqueada: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (desbloqueada) {
                Color(0xFFE5F5EA)
            } else {
                Color(0xFFE8EEEC)
            }
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = titulo,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = descricao,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = progresso,
                style = MaterialTheme.typography.labelSmall,
                color = if (desbloqueada) {
                    Color(0xFF008A4A)
                } else {
                    Color(0xFF66736D)
                }
            )

            if (desbloqueada) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "✓ Desbloqueada",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF008A4A)
                )
            }
        }
    }
}

private data class NivelLeafy(
    val emoji: String,
    val nome: String,
    val descricao: String,
    val xpMinimo: Int,
    val xpProximo: Int?
)

private val niveisLeafy = listOf(
    NivelLeafy(
        "🐜",
        "Formiga Operária",
        "Pequenas ações, grandes resultados.",
        0,
        100
    ),
    NivelLeafy(
        "🦫",
        "Castor Engenheiro",
        "Construindo soluções sustentáveis.",
        100,
        300
    ),
    NivelLeafy(
        "🐺",
        "Lobo Guardião",
        "Protetor da alcateia e do território.",
        300,
        600
    ),
    NivelLeafy(
        "🦅",
        "Condor dos Andes",
        "Visão ampla sobre o planeta.",
        600,
        1000
    ),
    NivelLeafy(
        "🐢",
        "Tartaruga Milenar",
        "Sabedoria e preservação dos mares.",
        1000,
        1500
    ),
    NivelLeafy(
        "🐦‍🔥",
        "Fênix",
        "A Terra renascendo das cinzas.",
        1500,
        null
    )
)