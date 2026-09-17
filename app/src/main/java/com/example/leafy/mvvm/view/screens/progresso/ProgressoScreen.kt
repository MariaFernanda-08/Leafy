package com.example.leafy.mvvm.view.screens.progresso

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9F6))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Meu Progresso",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Continue cuidando do planeta!🌍",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF496057)
        )
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF7EEFF)
            ),
            shape = RoundedCornerShape(18.dp)
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
                        text = "🦫",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Column(
                        modifier = Modifier.padding(start = 14.dp)
                    ) {
                        Text(
                            text = "Castor Engenheiro",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF64159C)
                        )

                        Text(
                            text = "Construindo soluções sustentáveis.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF8B20C7)
                        )

                        Text(
                            text = "Nível 2",
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
                        text = "245 XP",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFF64159C)
                    )

                    Text(
                        text = "Próximo: Lobo Guardião (300 XP)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF8B20C7)
                    )
                }

                LinearProgressIndicator(
                    progress = { 0.73f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = Color(0xFFB21BD6),
                    trackColor = Color(0xFFE8D1F5)
                )

                Text(
                    text = "73%",
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF64159C)
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF5EC)
            ),
            shape = RoundedCornerShape(18.dp)
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
                        text = "7 dias consecutivos usando o app!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFB84A25)
                    )

                    Text(
                        text = "Continue assim para ganhar +20 XP por dia",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB84A25)
                    )

                    Text(
                        text = "💎 Ganhe XP",
                        style = MaterialTheme.typography.titleLarge
                    )

                    AcaoXpCard(
                        emoji = "☆",
                        titulo = "Cadastrar Produtos",
                        descricao = "Sugira novos produtos",
                        xp = "+5 XP",
                        cor = Color(0xFFEAF2FF)
                    )

                    AcaoXpCard(
                        emoji = "♧",
                        titulo = "Cadastrar Lixeiras",
                        descricao = "Ajude a mapear locais",
                        xp = "+10 XP",
                        cor = Color(0xFFEAF8F0)
                    )

                    AcaoXpCard(
                        emoji = "🏆",
                        titulo = "Confirmar Informações",
                        descricao = "Valide dados de outros usuários",
                        xp = "+15 XP / -15 XP",
                        cor = Color(0xFFF8EEFF)
                    )

                    AcaoXpCard(
                        emoji = "📈",
                        titulo = "Usar Diariamente",
                        descricao = "Mantenha sua sequência ativa",
                        xp = "+20 XP",
                        cor = Color(0xFFFFF8E5)
                    )

                    Text(
                        text = "🏆 Níveis",
                        style = MaterialTheme.typography.titleLarge
                    )

                    NivelCard(
                        emoji = "🐜",
                        nome = "Formiga Operária",
                        descricao = "Pequenas ações, grandes resultados.",
                        xp = "0 - 99 XP",
                        atual = false
                    )

                    NivelCard(
                        emoji = "🦫",
                        nome = "Castor Engenheiro",
                        descricao = "Construindo soluções sustentáveis.",
                        xp = "100 - 299 XP",
                        atual = true
                    )

                    NivelCard(
                        emoji = "🐺",
                        nome = "Lobo Guardião",
                        descricao = "Protetor da alcateia e do território.",
                        xp = "300 - 599 XP",
                        atual = false
                    )

                    NivelCard(
                        emoji = "🦅",
                        nome = "Condor dos Andes",
                        descricao = "Visão ampla sobre o planeta.",
                        xp = "600 - 999 XP",
                        atual = false
                    )

                    NivelCard(
                        emoji = "🐢",
                        nome = "Tartaruga Milenar",
                        descricao = "Sabedoria e preservação dos mares.",
                        xp = "1000 - 1999 XP",
                        atual = false
                    )

                    NivelCard(
                        emoji = "🔥",
                        nome = "Fênix",
                        descricao = "A Terra renascendo das cinzas.",
                        xp = "2000 - ∞ XP",
                        atual = false
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "🏅 Conquistas",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "4/10",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color(0xFF8A5A00)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        ConquistaCard(
                            emoji = "🌱",
                            titulo = "Primeiro Passo",
                            descricao = "Cadastre seu primeiro produto",
                            desbloqueada = true,
                            modifier = Modifier.weight(1f)
                        )

                        ConquistaCard(
                            emoji = "🗺️",
                            titulo = "Explorador",
                            descricao = "Cadastre 5 lixeiras",
                            desbloqueada = true,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        ConquistaCard(
                            emoji = "🔒",
                            titulo = "Verificador",
                            descricao = "Confirme 10 informações",
                            desbloqueada = false,
                            modifier = Modifier.weight(1f)
                        )

                        ConquistaCard(
                            emoji = "🔥",
                            titulo = "Persistente",
                            descricao = "Use o app por 7 dias seguidos",
                            desbloqueada = true,
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
                            desbloqueada = true,
                            modifier = Modifier.weight(1f)
                        )

                        ConquistaCard(
                            emoji = "🔒",
                            titulo = "Reciclador Mestre",
                            descricao = "Escaneie 50 símbolos",
                            desbloqueada = false,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
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
        shape = RoundedCornerShape(16.dp)
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
                Color(0xFFF9EEFF)
            } else {
                Color(0xFFF0F5F3)
            }
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (atual) 3.dp else 0.dp
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
                        style = MaterialTheme.typography.titleMedium
                    )

                    if (atual) {
                        Text(
                            text = "  ATUAL",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF8B20C7)
                        )
                    }
                }

                Text(
                    text = descricao,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = xp,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF66736D)
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
    desbloqueada: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (desbloqueada) {
                Color(0xFFFFFAE8)
            } else {
                Color(0xFFF0F4F3)
            }
        ),
        shape = RoundedCornerShape(16.dp)
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
                style = MaterialTheme.typography.bodySmall
            )

            if (desbloqueada) {
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "✓ Desbloqueada",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF008A4A)
                )
            }
        }
    }
}