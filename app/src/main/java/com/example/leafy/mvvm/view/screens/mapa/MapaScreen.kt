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
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import android.location.Location
import android.location.LocationManager
import android.location.LocationListener
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface

@Composable
fun MapaScreen(
    paddingValues: PaddingValues,
    viewModel: PontoColetaViewModel = viewModel()
) {
   val pontos by viewModel.pontos.collectAsState()
   LaunchedEffect(Unit) {
       viewModel.buscarPontos()
   }

    val context = LocalContext.current
    var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val ultimaLocalizacao = remember { mutableStateOf<Location?>(null)}
    val locationListener = remember {
        LocationListener { location ->
            ultimaLocalizacao.value = location
        }
    }

    LaunchedEffect(Unit) {
        if (
            ContextCompat.checkSelfPermission(
                context,Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000L,
                1f,
                locationListener
            )
        }
    }

    val permissaoLocalizacao = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
    {
        permissoes ->
            val permitida =
                permissoes[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissoes[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            if (permitida){

            }
    }

    LaunchedEffect(Unit) {
        val temPermissao =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        if (!temPermissao){
            permissaoLocalizacao.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
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
                    height: 230px;
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
                
                .leafy-controle-localizacao {
                    width: 45px;
                    height: 45px;

                    background: white;
                    border-radius: 8px;

                    display: flex;
                    align-items: center;
                    justify-content: center;

                    font-size: 22px;

                    box-shadow: 0 2px 6px rgba(0,0,0,0.3);

                    cursor: pointer;
                }
                
                .vermelho {
                    background-color: #F44336;
                }
                
                .filtros-titulo {
                    margin: 0;
                    padding: 12px 16px 4px 16px;
                    font-family: sans-serif;
                    font-size: 16px;
                    font-weight: bold;
                    color: #17211C;    
                }
                
                .contador-pontos{
                    padding: 8px 16px 6px 16px;
                    font-family: sans-serif;
                    font-size: 14px;  
                    color: #4F5B55;    
                }
                
                #filtros {
                    width: 100%;
                    box-sizing: border-box;
                    display: flex;
                    gap: 8px;
                    overflow-x: auto;
                    padding: 10px;
                    margin: 0;
                }

                #filtros button {
                    border: none;
                    background: white;
                    padding: 10px 14px;

                    border-radius: 20px;

                    font-size: 14px;
                    font-weight: bold;

                    box-shadow: 0 2px 6px rgba(0,0,0,0.25);

                    white-space: nowrap;
                }

                #filtros button:active {
                    transform: scale(0.95);
                }
                
                #filtros button.ativo {
                    background: #4CAF50;
                    color: white;
                }
                
                .lista-pontos{
                    padding: 4px 16px 20px 16px;
                }
                
                .card-ponto{
                    background: #F0FFF8;
                    border: 1px solid #00C767;
                    border-radius: 14px;
                    padding: 16px;
                    margin-bottom: 12px;
                    font-family: sans-serif;    
                }
                
                .topo-ponto{
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-bottom: 10px;    
                }
                
                .topo-ponto strong {
                    font-size: 16px;
                    color: #17211C;
                }
                
                .materiais {
                    display: flex;
                    flex-wrap: wrap;
                    gap: 5px;
                    margin-bottom: 10px;
                }

                .materiais span {
                    background: #D8FBE9;
                    border: 1px solid #55D99A;
                    color: #087A45;
                    padding: 4px 8px;
                    border-radius: 7px;
                    font-size: 11px;
                }

                .info-ponto {
                    color: #4F5B55;
                    font-size: 13px;
                    margin-top: 7px;
                }

                .botao-como-chegar {
                    width: 100%;
                    margin-top: 14px;
                    padding: 10px;

                    border: none;
                    border-radius: 7px;

                    background: #00A65A;
                    color: white;

                    font-size: 14px;
                    font-weight: bold;
                }
                
                .status-ponto {
                    display: inline-block;
                    padding: 4px 8px;
                    border-radius: 10px;
                    font-size: 11px;
                    font-weight: bold;
                    margin-bottom: 8px;
                }

                .status-aberto {
                    background: #E8F5E9;
                    color: #2E7D32;
                }

                .status-fechado {
                    background: #FFF3E0;
                    color: #EF6C00;
                }
                
            </style>

            <script src="https://cdn.jsdelivr.net/npm/leaflet@1.9.4/dist/leaflet.js"></script>

        </head>

        <body>
        
                <div id="map"></div>
                
                <div class="filtros-titulo">🔎 Filtrar por tipo de resíduo</div>
                
                <div id="filtros">
                    <button data-filtro="todos" onclick="filtrarPontos('todos')">Todos</button>
                    <button data-filtro="reciclaveis" onclick="filtrarPontos('reciclaveis')">
                        ♻️ Recicláveis
                    </button>
                    <button data-filtro="eletronicos" onclick="filtrarPontos('eletronicos')">
                        🔌 Eletrônicos
                    </button>
                    <button data-filtro="moveis" onclick="filtrarPontos('moveis')">
                        🪑 Móveis
                    </button>
                    <button data-filtro="entulho" onclick="filtrarPontos('entulho')">
                        🧱 Entulho
                    </button>
                </div>
                
                <div class="contador-pontos" id="contador-pontos"> 0 ponto(s) de coleta encontrados </div>
                <div class="lista-pontos" id="lista-pontos"></div>

            <script>
                var map
                var iconeAberto
                var iconeFechado
                var pontosRecebidos = []
                var mapaPronto = false
                var marcadorUsuario = null
                
                function centralizarMinhaLocalizacao() {

                    if (marcadorUsuario !== null) {

                        var posicao = marcadorUsuario.getLatLng();

                        map.setView(
                            [posicao.lat, posicao.lng],
                            14
                        );
                    }
                }
                
                var iconeUsuario = L.divIcon({
                    className: 'marcador-usuario',
                    html: '<div class="marcador vermelho"></div>',
                    iconSize: [24, 24],
                    iconAnchor: [12, 12]
                });
                
                function receberLocalizacao(latitude, longitude) {
                    if (!mapaPronto) {
                        return;
                    }
                
                    if (marcadorUsuario !== null) {
                        map.removeLayer(marcadorUsuario);
                    }
                
                    marcadorUsuario = L.marker(
                        [latitude, longitude],
                        {icon: iconeUsuario}
                    )
                    .addTo(map)
                    .bindPopup(
                        '<b>📍 Você está aqui</b>'
                    );
                
                    map.setView(
                        [latitude, longitude],
                        14
                    );
                } 

                function adicionarPontos() {

                    if (!mapaPronto || pontosRecebidos.length === 0) {
                        return;
                    }

                    pontosRecebidos.forEach(function(ponto) {

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

                var filtroAtual = 'todos';
                var marcadoresPontos = [];

                function pontoCombinaComFiltro(ponto){
                    if(filtroAtual === 'todos'){
                        return true;
                    }
                    
                    var materiais = (ponto.materiais_aceitos || '').toLowerCase();
                    
                    if(
                        filtroAtual === 'reciclaveis' &&
                        ( 
                            materiais.includes('papel') || 
                            materiais.includes('plástico') || 
                            materiais.includes('plastico') || 
                            materiais.includes('vidro') || 
                            materiais.includes('metal')
                        )
                    ) {
                        return true;
                    }
                    
                    if(
                        filtroAtual === 'eletronicos' && 
                        (
                            materiais.includes('eletrônico') ||   
                            materiais.includes('eletronico') 
                        ) 
                    ) {
                        return true;
                    }
                    
                    if(
                        filtroAtual === 'moveis' && 
                        (
                            materiais.includes('móveis') ||   
                            materiais.includes('moveis') 
                        ) 
                    ) {
                        return true;
                    }
                    
                    if(
                        filtroAtual === 'entulho' && 
                         materiais.includes('entulho')
                    ) {
                        return true;
                    }
                    return false;
                }

                function atualizarCards(pontosFiltrados) {

                    var lista = document.getElementById('lista-pontos');

                    var contador = document.getElementById('contador-pontos');

                    contador.innerText =
                        pontosFiltrados.length +
                        ' ponto(s) de coleta encontrados';

                    lista.innerHTML = '';

                    pontosFiltrados.forEach(function(ponto) {

                        var materiais = ponto.materiais_aceitos || 'Não informado';

                        var listaMateriais = materiais
                            .split(/[,;]+/)
                            .map(function(material) {
                                return material.trim();
                                
                                if(material.length === 0){
                                    return '';
                                }
                                return material
                                    .toLowerCase()
                                    .replace(/\b\w/g, function(letra){
                                        return letra.toUpperCase();
                                    });
                            })
                            .filter(function(material) {
                                return material.length > 0;
                            });

                        var materiaisHtml = '';

                        listaMateriais.forEach(function(material) {
                            materiaisHtml +=
                                '<span>' + material + '</span>';
                        });

                        var classeStatus =
                            ponto.status === 'Aberto'
                                ? 'status-aberto'
                                : 'status-fechado';

                        var card =
                            '<div class="card-ponto">' +

                                '<div class="topo-ponto">' +
                                    '<strong>' +
                                        ponto.nome +
                                    '</strong>' +
                                '</div>' +

                                '<div class="status-ponto ' +
                                    classeStatus +
                                '">' +
                                    '● ' +
                                    ponto.status +
                                '</div>' +

                                '<div class="materiais">' +
                                    materiaisHtml +
                                '</div>' +

                                '<div class="info-ponto">' +
                                    '🕝 ' +
                                    (ponto.horario_funcionamento || 'Horário não informado') +
                                '</div>' +

                                '<button class="botao-como-chegar" onclick="Android.abrirGoogleMaps(' +
                                    ponto.latitude + ', ' +
                                    ponto.longitude +
                                ')">' + 
                                    '➤ &nbsp; Como Chegar' +
                                '</button>' +

                            '</div>';

                        lista.innerHTML += card;
                    });
                }

                function receberPontos(pontos) {

                    pontosRecebidos = pontos;
                    
                    marcadoresPontos.forEach(function(marcador) {
                        map.removeLayer(marcador);
                    });

                    marcadoresPontos = [];

                    var pontosFiltrados = [];

                    pontos.forEach(function(ponto) {
                        var mostrar = pontoCombinaComFiltro(ponto);
                        if (mostrar) {
                            pontosFiltrados.push(ponto);
                            var classe = ponto.status === 'Aberto'
                                ? 'verde'
                                : 'laranja';
                            var icone = L.divIcon({
                                className: 'marcador-ponto',
                                html: '<div class="marcador ' + classe + '"></div>',
                                iconSize: [26,26],
                                iconAnchor: [13,13]
                            });
                            var marcador = L.marker(
                                [ponto.latitude, ponto.longitude],
                                {icon: icone}
                            )
                            .addTo(map)
                            .bindPopup(
                                '<b>' + ponto.nome + '</b><br>' +
                                ponto.endereco + '<br><br>' +
                                '<b>Materiais aceitos:</b><br>' +
                                (ponto.materiais_aceitos || 'Não informado') +
                                '<br><br>' +
                                '<b>Status:</b> ' +
                                ponto.status        
                            );
                            marcadoresPontos.push(marcador);
                        }
                    });
                    atualizarCards(pontosFiltrados)
                }
                
                function filtrarPontos(filtro) {
                    filtroAtual = filtro;
                    
                    var botoes = document.querySelectorAll('#filtros button');

                    botoes.forEach(function(botao) {
                        botao.classList.remove('ativo');
                    });
                
                    var botaoSelecionado = document.querySelector(
                        '#filtros button[data-filtro="' + filtro + '"]'
                    );
                
                    if (botaoSelecionado !== null) {
                        botaoSelecionado.classList.add('ativo');
                    }
                    
                    receberPontos(pontosRecebidos);
                }

                setTimeout(function() {

                    map = L.map('map').setView(
                        [-22.1225, -51.3883],
                        13
                    );
                    
                    setTimeout(function() {
                        map.invalidateSize();
                    }, 300);

                    var controleLocalizacao = L.control({
                        position: 'bottomright'
                    });

                    controleLocalizacao.onAdd = function() {

                        var div = L.DomUtil.create(
                            'div',
                            'leafy-controle-localizacao'
                        );

                        div.innerHTML = '📍';

                        div.title = 'Minha localização';

                        div.onclick = function() {
                            centralizarMinhaLocalizacao();
                        };

                        L.DomEvent.disableClickPropagation(div);

                        return div;
                    };

                    controleLocalizacao.addTo(map);

                    var tiles = L.tileLayer(
                        'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                        {
                            maxZoom: 19,
                            attribution: '&copy; OpenStreetMap contributors'
                        }
                    );

                    tiles.addTo(map);
                    
                    iconeAberto = L.divIcon({
                        className: 'marcador-leafy',
                        html: '<div class="marcador verde"></div>',
                        iconSize: [20, 20],
                        iconAnchor: [10, 10]
                    });

                    iconeFechado = L.divIcon({
                        className: 'marcador-leafy',
                        html: '<div class="marcador laranja"></div>',
                        iconSize: [20, 20],
                        iconAnchor: [10, 10]
                    });
                    
                    mapaPronto = true
                    receberPontos(pontosRecebidos)
                    
                }, 1000);

            </script>

        </body>
        </html>
    """.trimIndent()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
        Text(
            text = "Pontos de Coleta",
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp
            ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Encontre locais próximos para descartar seus resíduos",
            modifier = Modifier.padding(
                start = 16.dp,
                top = 4.dp,
                end = 16.dp,
                bottom = 12.dp
            ),
            fontSize = 14.sp,
            color = Color(0xFF4F5B55)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(18.dp)),
            factory = { context ->

                WebView(context).apply {

                    webViewClient = object : WebViewClient(){
                        override fun onPageFinished(
                            view: WebView?,
                            url: String?
                        ){
                            super.onPageFinished(view,url)
                            val json = Gson().toJson(pontos)
                            evaluateJavascript("receberPontos($json);", null)
                        }
                    }

                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true

                    settings.allowFileAccess = true
                    settings.allowContentAccess = true

                    settings.userAgentString =
                        "Leafy/1.0 Android App"

                    addJavascriptInterface(object{
                        @JavascriptInterface
                        fun abrirGoogleMaps(latitude: Double, longitude: Double){
                            val uri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$latitude,$longitude")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                            context.startActivity(intent)
                        }
                    }, "Android")

                    loadDataWithBaseURL(
                        "https://cdn.jsdelivr.net/",
                        html,
                        "text/html",
                        "UTF-8",
                        null
                    )
                }
            },
            update = { webView ->
                if (pontos.isNotEmpty()) {
                    val json = Gson().toJson(pontos)
                    webView.evaluateJavascript(
                        "receberPontos($json);", null
                    )
                }
                ultimaLocalizacao.value?.let { localizacao ->
                    webView.evaluateJavascript("receberLocalizacao(${localizacao.latitude}, ${localizacao.longitude});", null)
                }
            }
        )
    }
}