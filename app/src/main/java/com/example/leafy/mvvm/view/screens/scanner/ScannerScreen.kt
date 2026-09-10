package com.example.leafy.mvvm.view.screens.scanner

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import androidx.camera.core.ExperimentalGetImage
import androidx.annotation.OptIn
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.viewmodel.ScannerViewModel
import androidx.navigation.NavController

@Composable
fun ScannerScreen(
    navController: NavController,
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

    val launcherPermissao = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { permitido ->
        temPermissao = permitido
    }

    if (temPermissao) {
        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            lifecycleOwner = lifecycleOwner,
            onCodigoDetectado = { codigo ->
                viewModel.buscarCodigo(codigo) { residuo ->
                    if (residuo != null) {
                        Log.d(
                            "LEAFY_SCANNER",
                            "Navegando para detalhes/${residuo.id}"
                        )
                        navController.navigate("detalhes/${residuo.id}")
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
        Column {
            Text("Permissão da câmera necessária")

            Button(
                onClick = {
                    launcherPermissao.launch(Manifest.permission.CAMERA)
                }
            ) {
                Text("Permitir câmera")
            }
        }
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

                val scanner = BarcodeScanning.getClient()

                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(context)){
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
    Log.d("LEAFY_SCANNER", "Analisando imagem da câmera")

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
