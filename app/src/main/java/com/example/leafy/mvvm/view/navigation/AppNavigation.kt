package com.example.leafy.mvvm.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.leafy.mvvm.view.components.LeafyLayout
import com.example.leafy.mvvm.view.screens.autenticacao.CadastroScreen
import com.example.leafy.mvvm.view.screens.autenticacao.LoginScreen
import com.example.leafy.mvvm.view.screens.consulta.ConsultaScreen
import com.example.leafy.mvvm.view.screens.detalhes.DetalhesResiduoScreen
import com.example.leafy.mvvm.view.screens.educacao.EducacaoScreen
import com.example.leafy.mvvm.view.screens.home.HomeScreen
import com.example.leafy.mvvm.view.screens.mapa.MapaScreen
import com.example.leafy.mvvm.view.screens.progresso.ProgressoScreen
import com.example.leafy.mvvm.view.screens.scanner.ScannerScreen
import com.example.leafy.mvvm.viewmodel.DetalhesResiduoViewModel
import com.example.leafy.mvvm.viewmodel.UsuarioViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                viewModel = usuarioViewModel,
                onLoginSucesso = {
                    navController.navigate("home")
                },
                onCadastroClick = {
                    navController.navigate("cadastro")
                }
            )
        }

        composable("cadastro") {
            CadastroScreen(
                onCadastroSucesso = {
                    navController.navigate("home") {
                        popUpTo("cadastro") {
                            inclusive = true
                        }
                    }
                },
                onVoltarLogin = {
                    navController.navigate("login")
                }
            )
        }

        composable("home") {
            val usuario = usuarioViewModel.usuario.collectAsState().value

            LeafyLayout(
                rotaAtual = "home",
                onHomeClick = {
                },
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
                mostrarSair = true,
                onSairClick = {
                    usuarioViewModel.sair()
                    navController.navigate("login") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }

            ) { paddingValues ->

                HomeScreen(
                    paddingValues = paddingValues,

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
                    },
                    usuario = usuario
                )
            }
        }

        composable("consulta") {

            LeafyLayout(
                rotaAtual = "consulta",
                onHomeClick = {
                    navController.navigate("home")
                },
                onConsultaClick = {
                },
                onScannerClick = {
                    navController.navigate("scanner")
                },
                onMapaClick = {
                    navController.navigate("mapa")
                },
                onEducacaoClick = {
                    navController.navigate("educacao")
                }
            ) { paddingValues ->

                ConsultaScreen(
                    navController = navController,
                    paddingValues = paddingValues
                )
            }
        }

        composable(
            route = "detalhes/{residuoId}",
            arguments = listOf(
                navArgument("residuoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val residuoId =
                backStackEntry.arguments?.getInt("residuoId")

            val viewModel: DetalhesResiduoViewModel = viewModel()

            LaunchedEffect(residuoId) {

                if (residuoId != null) {
                    viewModel.buscarResiduo(residuoId)
                }
            }

            val residuo = viewModel.residuo

            if (residuo != null) {

                LeafyLayout(
                    rotaAtual = "",
                    onHomeClick = {
                        navController.navigate("home")
                    },
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
                    }
                ) { paddingValues ->

                    DetalhesResiduoScreen(
                        residuo = residuo,
                        paddingValues = paddingValues
                    )
                }
            }
        }

        composable("scanner") {

            LeafyLayout(
                rotaAtual = "scanner",
                onHomeClick = {
                    navController.navigate("home")
                },
                onConsultaClick = {
                    navController.navigate("consulta")
                },
                onScannerClick = {
                },

                onMapaClick = {
                    navController.navigate("mapa")
                },
                onEducacaoClick = {
                    navController.navigate("educacao")
                }
            ) { paddingValues ->

                ScannerScreen(
                    navController = navController,
                    paddingValues = paddingValues
                )
            }
        }

        composable("mapa") {

            LeafyLayout(
                rotaAtual = "mapa",
                onHomeClick = {
                    navController.navigate("home")
                },
                onConsultaClick = {
                    navController.navigate("consulta")
                },
                onScannerClick = {
                    navController.navigate("scanner")
                },
                onMapaClick = {
                },
                onEducacaoClick = {
                    navController.navigate("educacao")
                }
            ) { paddingValues ->

                MapaScreen(
                    paddingValues = paddingValues
                )
            }
        }

        composable("educacao") {

            LeafyLayout(
                rotaAtual = "educacao",
                onHomeClick = {
                    navController.navigate("home")
                },
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
                }
            ) { paddingValues ->
                EducacaoScreen(
                    paddingValues = paddingValues
                )
            }
        }

        composable("progresso") {

            LeafyLayout(
                rotaAtual = "progresso",
                onHomeClick = {
                    navController.navigate("home")
                },
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
                }
            ) { paddingValues ->
                ProgressoScreen(
                    paddingValues = paddingValues
                )
            }
        }
    }
}