package com.example.leafy.mvvm.view.screens.mapa

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.leafy.mvvm.viewmodel.PontoColetaViewModel
import androidx.compose.runtime.LaunchedEffect
import com.google.gson.Gson

@Composable
fun MapaScreen(
    viewModel: PontoColetaViewModel = viewModel()
) {
   val pontos by viewModel.pontos.collectAsState()
   LaunchedEffect(Unit) {
       viewModel.buscarPontos()
   }

    val html = """
        <!DOCTYPE html>
        <html>
        <head>

            <meta name="viewport"
                  content="width=device-width, initial-scale=1.0">

            <link rel="stylesheet"
                  href="https://cdn.jsdelivr.net/npm/leaflet@1.9.4/dist/leaflet.css"/>

            <style>
                html, body {
                    width: 100%;
                    height: 100%;
                    margin: 0;
                    padding: 0;
                }

                #map {
                    width: 100%;
                    height: 100vh;
                    min-height: 500px;
                    margin: 0;
                    padding: 0;
                }

                #mensagem {
                    position: absolute;
                    top: 20px;
                    left: 20px;
                    right: 20px;
                    z-index: 9999;
                    background: white;
                    padding: 15px;
                    font-family: sans-serif;
                }
                   
                .marcador {
                    width: 20px;
                    height: 20px;
                    border-radius: 50%;
                    border: 3px solid white;
                    box-shadow: 0 2px 5px rgba(0,0,0,0.4);
                }

                .verde {
                    background-color: #4CAF50;
                }

                .laranja {
                    background-color: #FF9800;
                }
                
                .leafy-popup {
                    font-family: sans-serif;
                    min-width: 220px;
                }

                .leafy-titulo {
                    font-size: 18px;
                    font-weight: bold;
                    margin-bottom: 8px;
                    color: #2E7D32;
                }

                .leafy-status-aberto {
                    display: inline-block;
                    background: #E8F5E9;
                    color: #2E7D32;
                    padding: 5px 10px;
                    border-radius: 12px;
                    font-weight: bold;
                    margin-bottom: 10px;
                }

                .leafy-status-fechado {
                    display: inline-block;
                    background: #FFF3E0;
                    color: #EF6C00;
                    padding: 5px 10px;
                    border-radius: 12px;
                    font-weight: bold;
                    margin-bottom: 10px;
                }

                .leafy-info {
                    color: #444;
                    line-height: 1.5;
                }
                
            </style>

            <!-- Leaflet vem ANTES do nosso código -->
            <script src="https://cdn.jsdelivr.net/npm/leaflet@1.9.4/dist/leaflet.js"></script>

        </head>

        <body>

            <div id="mensagem">
                Carregando Leaflet...
            </div>

            <div id="map"></div>

            <script>
                document.getElementById("mensagem").innerHTML =
                    "JavaScript funcionando!";

                setTimeout(function() {

                    document.getElementById("mensagem").innerHTML =
                        "Criando mapa...";

                    var map = L.map('map').setView(
                        [-22.1225, -51.3883],
                        13
                    );

                    var tiles = L.tileLayer(
                        'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                        {
                            maxZoom: 19,
                            attribution: '&copy; OpenStreetMap contributors'
                        }
                    );
                    
                    tiles.on('tileerror', function(error) {
                        document.getElementById("mensagem").innerHTML =
                            "❌ ERRO AO CARREGAR TILES: " + error.error;
                    });

                    tiles.on('load', function() {
                        document.getElementById("mensagem").innerHTML =
                            "✅ TILES CARREGADOS!";
                    });

                    tiles.addTo(map);
                    
                    var iconeAberto = L.divIcon({
                        className: 'marcador-leafy',
                        html: '<div class="marcador verde"></div>',
                        iconSize: [20, 20],
                        iconAnchor: [10, 10]
                    });

                    var iconeFechado = L.divIcon({
                        className: 'marcador-leafy',
                        html: '<div class="marcador laranja"></div>',
                        iconSize: [20, 20],
                        iconAnchor: [10, 10]
                    });
                    
                    function receberPontos(pontos) {

                        console.log("Pontos recebidos:", pontos);

                        pontos.forEach(function(ponto) {

                            var icone;

                            if (ponto.status === "Aberto") {
                                icone = iconeAberto;
                            } else {
                                icone = iconeFechado;
                            }

                            L.marker(
                                [ponto.latitude, ponto.longitude],
                                { icon: icone }
                            )
                            .addTo(map)
                            .bindPopup(
                                '<div class="leafy-popup">' +
                                    '<div class="leafy-titulo">🌱 ' +
                                        ponto.nome +
                                    '</div>' +

                                    '<div class="' +
                                        (ponto.status === "Aberto"
                                            ? "leafy-status-aberto"
                                            : "leafy-status-fechado") +
                                    '">' +
                                        '● ' + ponto.status +
                                    '</div>' +

                                    '<div class="leafy-info">' +
                                        '<b>📍 Endereço</b><br>' +
                                        ponto.endereco +
                                        '<br><br>' +

                                        '<b>♻️ Aceita:</b><br>' +
                                        ponto.materiais_aceitos +
                                        '<br><br>' +

                                        '<b>ℹ️ Informações:</b><br>' +
                                        ponto.descricao +
                                    '</div>' +
                                '</div>'
                            );
                        });
                    }
                    
                    L.marker([-22.1164126, -51.4241028], {icon: iconeAberto})
                        .addTo(map)
                        .bindPopup(
                            '<div class="leafy-popup">' +
                                '<div class="leafy-titulo">🌱 Ecoponto Sabará</div>' +
                                '<div class="leafy-status-aberto">● Aberto</div>' +
                                '<div class="leafy-info">' +
                                    '<b>📍 Endereço</b><br>' +
                                    'Rua Adelino Rodrigues Gatto x Rua Afonso Vincoletto<br><br>' +
                        
                                    '<b>♻️ Aceita:</b><br>' +
                                    '• Entulho de pequenas obras<br>' +
                                    '• Móveis<br>' +
                                    '• Eletrônicos e eletrodomésticos<br>' +
                                    '• Madeira<br>' +
                                    '• Plásticos, papéis e metais<br><br>' +
                        
                                    '<b>⚠️ Não aceita:</b><br>' +
                                    'Resíduos perigosos, pneus e lixo orgânico.' +
                                '</div>' +
                            '</div>'
                        );
                        
                    L.marker([-22.1486263, -51.3821031], {icon: iconeFechado})
                        .addTo(map)
                        .bindPopup(
                            '<div class="leafy-popup">' +
                                '<div class="leafy-titulo">🌱 Ecoponto Cambuci</div>' +
                                '<div class="leafy-status-fechado">● Temporariamente fechado</div>' +
                                '<div class="leafy-info">' +
                                    '<b>📍 Endereço</b><br>' +
                                    'Avenida Dom Pedro I, nº 38<br><br>' +

                                    '<b>♻️ Aceita:</b><br>' +
                                    '• Papel, plástico, metal e vidro<br>' +
                                    '• Móveis usados<br>' +
                                    '• Entulho de pequenas obras<br>' +
                                    '• Pequenas quantidades de poda<br>' +
                                    '• Óleo de cozinha usado<br><br>' +

                                    '<b>⚠️ Não aceita:</b><br>' +
                                    'Resíduos industriais, hospitalares e produtos perigosos.' +
                                '</div>' +
                            '</div>'
                        );
                    
                }, 1000);

            </script>

        </body>
        </html>
    """.trimIndent()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->

            WebView(context).apply {

                webViewClient = object : WebViewClient(){
                    override fun onPageFinished(
                        view: WebView?,
                        url: String?
                    ){
                        super.onPageFinished(view,url)
                        val json = Gson().toJson(pontos)
                        evaluateJavascript("receberPontos($json;", null)
                    }
                }

                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true

                settings.allowFileAccess = true
                settings.allowContentAccess = true

                settings.userAgentString =
                    "Leafy/1.0 Android App"

                loadDataWithBaseURL(
                    "https://cdn.jsdelivr.net/",
                    html,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        }
    )
}