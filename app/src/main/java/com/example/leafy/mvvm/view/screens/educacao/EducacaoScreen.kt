package com.example.leafy.mvvm.view.screens.educacao

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
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
fun EducacaoScreen() {

    var itemAberto by remember {
        mutableStateOf<String?>(null)
    }

    val tiposReciclagem = listOf(

        TipoReciclagem(
            emoji = "♻️",
            nome = "Reciclagem de Plástico",
            descricao = "O plástico pode ser reciclado e transformado em novos produtos. Cada tipo de plástico possui um número de identificação de 1 a 7.",
            beneficios = listOf(
                "Reduz a poluição em oceanos e aterros",
                "Economiza petróleo, utilizado como matéria-prima",
                "Contribui para a geração de empregos na cadeia de reciclagem"
            )
        ),

        TipoReciclagem(
            emoji = "📄",
            nome = "Reciclagem de Papel",
            descricao = "O papel pode ser reciclado diversas vezes, reduzindo a necessidade de utilizar novas matérias-primas.",
            beneficios = listOf(
                "Preserva florestas",
                "Economiza energia na produção",
                "Reduz o consumo de água"
            )
        ),

        TipoReciclagem(
            emoji = "🥫",
            nome = "Reciclagem de Metal",
            descricao = "O alumínio pode ser reciclado diversas vezes sem perder suas propriedades.",
            beneficios = listOf(
                "Economiza energia em comparação à produção primária",
                "Reduz a extração de minérios",
                "Diminui a quantidade de resíduos enviados aos aterros"
            )
        ),

        TipoReciclagem(
            emoji = "🍾",
            nome = "Reciclagem de Vidro",
            descricao = "O vidro pode ser reciclado diversas vezes sem perder sua qualidade.",
            beneficios = listOf(
                "Não perde qualidade durante a reciclagem",
                "Economiza energia",
                "Reduz impactos ambientais associados à produção de vidro"
            )
        ),

        TipoReciclagem(
            emoji = "🌱",
            nome = "Compostagem (Orgânicos)",
            descricao = "Restos de alimentos e outros resíduos orgânicos podem ser transformados em adubo por meio da compostagem.",
            beneficios = listOf(
                "Reduz a quantidade de lixo doméstico",
                "Produz adubo natural",
                "Diminui a quantidade de resíduos orgânicos enviados aos aterros"
            )
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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
        
        item { Spacer(modifier = Modifier.height(4.dp)) }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0FFF8)
                ),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    Color(0xFF9BE8C5)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(26.dp),
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
                        exemplo = "Jornais, revistas, caixas de papelão, cadernos"
                    )

                    CorColetaCard(
                        cor = Color(0xFFED1C24),
                        titulo = "Vermelho - Plástico",
                        exemplo = "Garrafas PET, embalagens, sacolas plásticas"
                    )

                    CorColetaCard(
                        cor = Color(0xFF00A651),
                        titulo = "Verde - Vidro",
                        exemplo = "Garrafas, potes, frascos de vidro"
                    )

                    CorColetaCard(
                        cor = Color(0xFFF2B400),
                        titulo = "Amarelo - Metal",
                        exemplo = "Latas de alumínio, enlatados, tampinhas"
                    )

                    CorColetaCard(
                        cor = Color(0xFFC65D00),
                        titulo = "Marrom - Orgânico",
                        exemplo = "Restos de alimentos, cascas de frutas"
                    )

                    CorColetaCard(
                        cor = Color(0xFF596574),
                        titulo = "Cinza - Não Reciclável",
                        exemplo = "Lixo comum, papel higiênico, fraldas"
                    )
                }
            }
        }
        
        item{
            Spacer(modifier = Modifier.height(22.dp))
        }

        item {

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
                    itemAberto =
                        if (itemAberto == tipo.nome) {
                            null
                        } else {
                            tipo.nome
                        }
                }
            )
        }

        item { Spacer(modifier = Modifier.height(22.dp)) }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0FFF8)
                ),
                border = androidx.compose.foundation.BorderStroke(
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
                        texto = "Separe corretamente os resíduos e encaminhe-os para reciclagem."
                    )

                    DicaCard(
                        emoji = "💧",
                        titulo = "Economize Água",
                        texto = "Feche torneiras e reduza o tempo dos banhos."
                    )
                }
            }
        }

        item{Spacer(modifier = Modifier.height(22.dp))}

        item {

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
                        texto = "A reciclagem permite que materiais sejam aproveitados novamente, reduzindo a necessidade de novas matérias-primas."
                    )

                    Curiosidade(
                        emoji = "♻️",
                        titulo = "Pequenas atitudes",
                        texto = "Reduzir, reutilizar e reciclar são atitudes simples que podem contribuir para um consumo mais consciente."
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
                modifier = Modifier
                    .padding(end = 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cor
                )
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(20.dp)
                )
            }

            Column {

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
        border = androidx.compose.foundation.BorderStroke(
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
                    imageVector = if (aberto)
                        Icons.Default.KeyboardArrowUp
                    else
                        Icons.Default.KeyboardArrowDown,
                    contentDescription = if (aberto)
                        "Fechar"
                    else
                        "Abrir"
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEAF8F0))
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Text(
                text = emoji,
                modifier = Modifier.padding(end = 12.dp)
            )

            Column {

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
   Card(modifier = Modifier.fillMaxWidth(),
       colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFACB)),
       border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFEB38))
   ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(text = emoji, modifier = Modifier.padding(end = 12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = titulo, fontWeight = FontWeight.Bold)
                Text(text = texto, style = MaterialTheme.typography.bodyMedium)
            }
        }
   }
}