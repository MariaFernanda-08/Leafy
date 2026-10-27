
package com.example.leafy.mvvm.view.screens.educacao

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class TipoReciclagem(
    val emoji: String,
    val nome: String,
    val descricao: String,
    val beneficios: List<String>
)

@Composable
fun EducacaoScreen(
    paddingValues: PaddingValues
) {
    var itemAberto by remember {
        mutableStateOf<String?>(null)
    }

    val tiposReciclagem = listOf(
        TipoReciclagem(
            emoji = "♻️",
            nome = "Reciclagem de Plástico",
            descricao = "O plástico pode ser reciclado e transformado em novos produtos. Cada tipo possui características próprias.",
            beneficios = listOf(
                "Reduz a quantidade de resíduos descartados",
                "Pode economizar matérias-primas",
                "Contribui para a cadeia da reciclagem"
            )
        ),
        TipoReciclagem(
            emoji = "📄",
            nome = "Reciclagem de Papel",
            descricao = "O papel pode ser reciclado para produzir novos materiais, reduzindo a necessidade de matérias-primas.",
            beneficios = listOf(
                "Ajuda a preservar recursos naturais",
                "Pode economizar energia na produção",
                "Reduz a quantidade de resíduos"
            )
        ),
        TipoReciclagem(
            emoji = "🥫",
            nome = "Reciclagem de Metal",
            descricao = "Metais como o alumínio podem ser reciclados diversas vezes, desde que sejam encaminhados corretamente.",
            beneficios = listOf(
                "Pode economizar energia",
                "Reduz a necessidade de extrair minérios",
                "Diminui os resíduos enviados aos aterros"
            )
        ),
        TipoReciclagem(
            emoji = "🍾",
            nome = "Reciclagem de Vidro",
            descricao = "O vidro pode ser reciclado várias vezes, desde que seja separado e encaminhado corretamente.",
            beneficios = listOf(
                "Ajuda a economizar matérias-primas",
                "Pode reduzir o consumo de energia",
                "Contribui para a redução de resíduos"
            )
        ),
        TipoReciclagem(
            emoji = "🌱",
            nome = "Compostagem (Orgânicos)",
            descricao = "Restos de alimentos e outros resíduos orgânicos podem ser transformados em adubo por meio da compostagem.",
            beneficios = listOf(
                "Reduz a quantidade de lixo doméstico",
                "Produz adubo natural",
                "Diminui os resíduos orgânicos enviados aos aterros"
            )
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Sistema Educativo",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Aprenda sobre reciclagem e descarte consciente",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        item {
            Spacer(modifier = Modifier.height(4.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0FFF8)
                ),
                border = BorderStroke(
                    1.dp,
                    Color(0xFF9BE8C5)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "♻️  Cores da Coleta Seletiva",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    CorColetaCard(
                        cor = Color(0xFF4169E1),
                        titulo = "Azul - Papel",
                        exemplo = "Jornais, revistas, caixas de papelão e cadernos"
                    )

                    CorColetaCard(
                        cor = Color(0xFFED1C24),
                        titulo = "Vermelho - Plástico",
                        exemplo = "Garrafas PET, embalagens e sacolas plásticas"
                    )

                    CorColetaCard(
                        cor = Color(0xFF00A651),
                        titulo = "Verde - Vidro",
                        exemplo = "Garrafas, potes e frascos de vidro"
                    )

                    CorColetaCard(
                        cor = Color(0xFFF2B400),
                        titulo = "Amarelo - Metal",
                        exemplo = "Latas de alumínio, enlatados e tampinhas"
                    )

                    CorColetaCard(
                        cor = Color(0xFFC65D00),
                        titulo = "Marrom - Orgânico",
                        exemplo = "Restos de alimentos e cascas de frutas"
                    )

                    CorColetaCard(
                        cor = Color(0xFF596574),
                        titulo = "Cinza - Não Reciclável",
                        exemplo = "Rejeitos como papel higiênico e fraldas"
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "🗑️  Tipos de Reciclagem",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        items(tiposReciclagem) { tipo ->
            TipoReciclagemCard(
                tipo = tipo,
                aberto = itemAberto == tipo.nome,
                onClick = {
                    itemAberto = if (itemAberto == tipo.nome) {
                        null
                    } else {
                        tipo.nome
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0FFF8)
                ),
                border = BorderStroke(
                    1.dp,
                    Color(0xFF9BE8C5)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "🌿  Dicas Sustentáveis",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    DicaCard(
                        emoji = "♻️",
                        titulo = "Reduza",
                        texto = "Consuma menos e escolha produtos com menos embalagens."
                    )

                    DicaCard(
                        emoji = "🔄",
                        titulo = "Reutilize",
                        texto = "Dê nova vida a objetos antes de descartá-los."
                    )

                    DicaCard(
                        emoji = "♻️",
                        titulo = "Recicle",
                        texto = "Separe os resíduos e encaminhe-os para o destino adequado."
                    )

                    DicaCard(
                        emoji = "💧",
                        titulo = "Economize Água",
                        texto = "Feche torneiras e reduza o tempo dos banhos."
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "💡  Curiosidades Ambientais",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Curiosidade(
                    emoji = "🦺",
                    titulo = "Você sabia?",
                    texto = "Separar corretamente os resíduos facilita o trabalho de coleta e reciclagem."
                )

                Curiosidade(
                    emoji = "🌱",
                    titulo = "Menos lixo, mais recursos",
                    texto = "A reciclagem permite reaproveitar materiais e reduzir a necessidade de novas matérias-primas."
                )

                Curiosidade(
                    emoji = "♻️",
                    titulo = "Pequenas atitudes",
                    texto = "Reduzir, reutilizar e reciclar contribui para um consumo mais consciente."
                )
            }

            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}

@Composable
private fun CorColetaCard(
    cor: Color,
    titulo: String,
    exemplo: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = cor.copy(alpha = 0.12f)
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.padding(end = 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cor
                )
            ) {
                Spacer(
                    modifier = Modifier.padding(20.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = exemplo,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun TipoReciclagemCard(
    tipo: TipoReciclagem,
    aberto: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE1F7FF)
        ),
        border = BorderStroke(
            1.dp,
            Color(0xFF166CB1)
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
                    text = tipo.emoji,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = tipo.nome,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Medium
                )

                Icon(
                    imageVector = if (aberto) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = if (aberto) "Fechar" else "Abrir"
                )
            }

            if (aberto) {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = tipo.descricao,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Benefícios:",
                    fontWeight = FontWeight.Bold
                )

                tipo.beneficios.forEach { beneficio ->
                    Text(
                        text = "• $beneficio",
                        modifier = Modifier.padding(top = 6.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                if (tipo.nome == "Reciclagem de Vidro") {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Como descartar corretamente?",
                        fontWeight = FontWeight.Bold
                    )

                    OrientacaoVidro(
                        emoji = "♻️",
                        titulo = "Quais vidros podem ser reciclados?",
                        texto = "Garrafas, potes e alguns frascos podem ser reciclados. Espelhos, cerâmicas e lâmpadas podem exigir outra destinação."
                    )

                    OrientacaoVidro(
                        emoji = "🧼",
                        titulo = "Antes de descartar",
                        texto = "Esvazie os recipientes, retire o excesso de resíduos e confira se o ponto de coleta aceita o material."
                    )

                    OrientacaoVidro(
                        emoji = "⚠️",
                        titulo = "Vidro quebrado",
                        texto = "Não pegue os cacos com as mãos desprotegidas. Coloque-os em uma embalagem resistente, feche-a e identifique: “Cuidado: vidro quebrado”."
                    )

                    OrientacaoVidro(
                        emoji = "💡",
                        titulo = "Importante",
                        texto = "Nem todo vidro é aceito na coleta seletiva convencional. Consulte as orientações do município ou do ponto de entrega."
                    )
                }
            }
        }
    }
}

@Composable
private fun OrientacaoVidro(
    emoji: String,
    titulo: String,
    texto: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF0FFF8)
        ),
        border = BorderStroke(
            1.dp,
            Color(0xFFD5F0E2)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = emoji,
                modifier = Modifier.padding(end = 10.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF24443A)
                )

                Text(
                    text = texto,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun DicaCard(
    emoji: String,
    titulo: String,
    texto: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        border = BorderStroke(
            1.dp,
            Color(0xFFEAF8F0)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = emoji,
                modifier = Modifier.padding(end = 12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = texto,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun Curiosidade(
    emoji: String,
    titulo: String,
    texto: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFACB)
        ),
        border = BorderStroke(
            1.dp,
            Color(0xFFFFEB38)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = emoji,
                modifier = Modifier.padding(end = 12.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = texto,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
