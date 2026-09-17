package com.example.leafy.mvvm.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leafy.mvvm.view.screens.consulta.ConsultaScreen
import com.example.leafy.mvvm.view.screens.educacao.EducacaoScreen
import com.example.leafy.mvvm.view.screens.home.HomeScreen
import com.example.leafy.mvvm.view.screens.mapa.MapaScreen
import com.example.leafy.mvvm.view.screens.progresso.ProgressoScreen
import com.example.leafy.mvvm.view.screens.scanner.ScannerScreen
import com.example.leafy.mvvm.view.screens.detalhes.DetalhesResiduoScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leafy.mvvm.viewmodel.DetalhesResiduoViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home")
    {
        composable("home") {
            HomeScreen(
                onConsultaClick = {
                    navController.navigate("consulta")
                },
                onScannerClick = {
                    navController.navigate("scanner")
                },
                onMapaClick = {
                    navController.navigate("mapa")
                },
                onEducacaoClick = {
                    navController.navigate("educacao")
                },
                onProgressoClick = {
                    navController.navigate("progresso")
                }
            )
        }

        composable("consulta") {
            ConsultaScreen(navController = navController)
        }
        composable(route = "detalhes/{residuoId}",
            arguments = listOf(navArgument("residuoId")
            {
                type = NavType.IntType
            }
            )
        ) { backStackEntry ->
            val residuoId = backStackEntry.arguments?.getInt("residuoId")
            val viewModel: DetalhesResiduoViewModel = viewModel()

            LaunchedEffect(residuoId) {
                if (residuoId != null) {
                    viewModel.buscarResiduo(residuoId)
                }
            }

            val residuo = viewModel.residuo
            if (residuo != null) {
                DetalhesResiduoScreen(
                    residuo = residuo,
                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable("scanner") {
            ScannerScreen(navController = navController)
        }
        composable("mapa") {
            MapaScreen()
        }
        composable("educacao") {
            EducacaoScreen()
        }
        composable("progresso") {
            ProgressoScreen()
        }
    }
}