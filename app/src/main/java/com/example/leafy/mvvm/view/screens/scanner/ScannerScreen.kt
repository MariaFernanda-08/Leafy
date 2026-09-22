package com.example.leafy.mvvm.view.screens.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.leafy.mvvm.viewmodel.ScannerViewModel
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import androidx.compose.ui.unit.sp

@Composable
fun ScannerScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: ScannerViewModel = viewModel())
{
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var temPermissao by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    var scannerAtivo by remember {
        mutableStateOf(false)
    }

    val launcherPermissao = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { permitido ->
        temPermissao = permitido

        if (permitido){
            scannerAtivo = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F9F6))
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Scanner de Código de Barras",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF17213A)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Escaneie o código de barras da embalagem",
            fontSize = 14.sp,
            color = Color(0xFF5F6875)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF0F6FF)
            ),
            shape = RoundedCornerShape(14.dp),
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                Color(0xFFBFD8FF)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (scannerAtivo && temPermissao) {

                    CameraPreview(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                2.dp,
                                Color(0xFF00A65A),
                                RoundedCornerShape(14.dp)
                            ),
                        lifecycleOwner = lifecycleOwner,
                        onCodigoDetectado = { codigo ->

                            viewModel.buscarCodigo(codigo) { residuo ->

                                if (residuo != null) {

                                    Log.d(
                                        "LEAFY_SCANNER",
                                        "Navegando para detalhes/${residuo.id}"
                                    )

                                    navController.navigate(
                                        "detalhes/${residuo.id}"
                                    )

                                } else {

                                    Log.d(
                                        "LEAFY_SCANNER",
                                        "Produto não encontrado no banco"
                                    )
                                }
                            }
                        }
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF111827))
                            .border(
                                2.dp,
                                Color(0xFF00A65A),
                                RoundedCornerShape(14.dp)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "Câmera",
                            modifier = Modifier.size(58.dp),
                            tint = Color(0xFF9CA3AF)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Pronto para escanear",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        if (temPermissao) {
                            scannerAtivo = true
                        } else {
                            launcherPermissao.launch(
                                Manifest.permission.CAMERA
                            )
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4C3BFF)
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = if (scannerAtivo)
                            "Scanner ativo"
                        else
                            "Iniciar Scanner",
                        fontWeight = FontWeight.Bold
                    )
            }
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF0F6FF)
            ),
            shape = RoundedCornerShape(14.dp),
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                Color(0xFFBFD8FF)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "Como usar o Scanner",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF173A9B),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))

                InstrucaoScanner(
                    numero = "1.",
                    texto = "Localize o código de barras na embalagem."
                )

                Spacer(modifier = Modifier.height(12.dp))

                InstrucaoScanner(
                    numero = "2.",
                    texto = "Clique no botão \"Iniciar Scanner\"."
                )

                Spacer(modifier = Modifier.height(12.dp))

                InstrucaoScanner(
                    numero = "3.",
                    texto = "Posicione o código dentro da área da câmera."
                )

                Spacer(modifier = Modifier.height(12.dp))

                InstrucaoScanner(
                    numero = "4.",
                    texto = "Aguarde a identificação do produto."
                )

                Spacer(modifier = Modifier.height(12.dp))

                InstrucaoScanner(
                    numero = "5.",
                    texto = "Confira as informações e as instruções de descarte."
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun InstrucaoScanner(
    numero: String,
    texto: String
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ){
        Text(
            text = numero,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF173A9B),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = texto,
            color = Color(0xFF263A91),
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@OptIn(ExperimentalGetImage::class)
@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    lifecycleOwner: androidx.lifecycle.LifecycleOwner,
    onCodigoDetectado: (String) -> Unit
) {
    val context = LocalContext.current
    var codigoJaDetectado = false
    AndroidView(
        modifier = modifier,
        factory = { contextView ->
            val previewView = PreviewView(contextView)

            val cameraProviderFuture =
                ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({

                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                val scanner = com.google.mlkit.vision.barcode.BarcodeScanning
                    .getClient()

                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(context))
                {
                    imageProxy ->
                    processarImagem(
                        scanner =  scanner,
                        imageProxy = imageProxy,
                        onCodigoDetectado = {codigo ->
                            if (!codigoJaDetectado){
                                codigoJaDetectado = true
                                onCodigoDetectado(codigo)
                            }
                        }
                    )
                }

                val cameraSelector =
                    CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()

                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )

                } catch (e: Exception) {
                    Log.e(
                        "LEAFY_CAMERA",
                        "Erro ao iniciar câmera",
                        e
                    )
                }

            }, ContextCompat.getMainExecutor(context))

            previewView
        }
    )
}

@OptIn(ExperimentalGetImage::class)
private fun processarImagem(
    scanner: com.google.mlkit.vision.barcode.BarcodeScanner,
    imageProxy: ImageProxy,
    onCodigoDetectado: (String) -> Unit
) {
    Log.d(
        "LEAFY_SCANNER",
        "Analisando imagem da câmera"
    )

    val mediaImage = imageProxy.image

    if (mediaImage != null) {

        val image = InputImage.fromMediaImage(
            mediaImage,
            imageProxy.imageInfo.rotationDegrees
        )

        scanner.process(image)
            .addOnSuccessListener { codigos ->

                for (codigo in codigos) {

                    val valor = codigo.rawValue

                    if (valor != null) {
                        Log.d(
                            "LEAFY_SCANNER",
                            "Código detectado: $valor"
                        )
                        onCodigoDetectado(valor)
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    "LEAFY_SCANNER",
                    "Erro ao analisar código",
                    e
                )
            }
            .addOnCompleteListener {
                imageProxy.close()
            }

    } else {
        imageProxy.close()
    }
}