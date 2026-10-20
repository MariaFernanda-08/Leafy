package com.example.leafy.mvvm.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.leafy.mvvm.view.screens.autenticacao.TelaCarregamento
import com.example.leafy.mvvm.view.screens.cadastroResiduo.CadastrarResiduoScreen
import com.example.leafy.mvvm.view.screens.consulta.ConsultaScreen
import com.example.leafy.mvvm.view.screens.detalhes.DetalhesResiduoScreen
import com.example.leafy.mvvm.view.screens.educacao.EducacaoScreen
import com.example.leafy.mvvm.view.screens.home.HomeScreen
import com.example.leafy.mvvm.view.screens.mapa.MapaScreen
import com.example.leafy.mvvm.view.screens.progresso.ProgressoScreen
import com.example.leafy.mvvm.view.screens.scanner.ScannerScreen
import com.example.leafy.mvvm.view.screens.admin.SugestoesAdminScreen
import com.example.leafy.mvvm.viewmodel.DetalhesResiduoViewModel
import com.example.leafy.mvvm.viewmodel.SugestoesAdminViewModel
import com.example.leafy.mvvm.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") {

            LaunchedEffect(Unit) {
                val sessaoAtiva = usuarioViewModel.restaurarSessao()

                navController.navigate(
                    if (sessaoAtiva) "home" else "login"
                ) {
                    popUpTo("inicio") {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            TelaCarregamento()
        }

        // LOGIN
        composable("login") {
            LoginScreen(
                viewModel = usuarioViewModel,

                onLoginSucesso = {
                    navController.navigate("home") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                },

                onCadastroClick = {
                    navController.navigate("cadastro")
                }
            )
        }

        // CADASTRO
        composable("cadastro") {
            CadastroScreen(
                onCadastroSucesso = {
                    usuarioViewModel.carregarUsuarioAtual()

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

        // HOME
        composable("home") {
            val usuario = usuarioViewModel.usuario.collectAsState().value

            LaunchedEffect(usuario) {
                println("Usuário na Home: ${usuario?.nome}")
            }

            LeafyLayout(
                rotaAtual = "home",

                onHomeClick = {},

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

                    usuario = usuario,

                    onSugestoesAdminClick = {
                        navController.navigate("sugestoesAdmin")
                    }
                )
            }
        }

        // CONSULTA DE RESÍDUOS
        composable("consulta") {
            LeafyLayout(
                rotaAtual = "consulta",

                onHomeClick = {
                    navController.navigate("home")
                },

                onConsultaClick = {},

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

        // DETALHES DO RESÍDUO
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

        // CADASTRO DE RESÍDUO NÃO ENCONTRADO
        composable(
            route = "cadastrarResiduo/{codigoBarras}",
            arguments = listOf(
                navArgument("codigoBarras") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val codigoBarras =
                backStackEntry.arguments?.getString("codigoBarras") ?: ""

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

                CadastrarResiduoScreen(
                    codigoBarras = codigoBarras,
                    paddingValues = paddingValues,

                    onVoltar = {
                        navController.navigate("scanner") {
                            popUpTo("scanner") {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }

        // SCANNER
        composable("scanner") {
            LeafyLayout(
                rotaAtual = "scanner",

                onHomeClick = {
                    navController.navigate("home")
                },

                onConsultaClick = {
                    navController.navigate("consulta")
                },

                onScannerClick = {},

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

        // MAPA
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

                onMapaClick = {},

                onEducacaoClick = {
                    navController.navigate("educacao")
                }
            ) { paddingValues ->

                MapaScreen(
                    paddingValues = paddingValues
                )
            }
        }

        // EDUCAÇÃO AMBIENTAL
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

                onEducacaoClick = {}
            ) { paddingValues ->

                EducacaoScreen(
                    paddingValues = paddingValues
                )
            }
        }

        // PROGRESSO DO USUÁRIO
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

        // SUGESTÕES DO ADMINISTRADOR
        composable("sugestoesAdmin") {
            val usuario = usuarioViewModel.usuario.collectAsState().value

            if (usuario?.tipoUsuario == "admin") {
                val adminViewModel: SugestoesAdminViewModel = viewModel()

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

                    SugestoesAdminScreen(
                        paddingValues = paddingValues,

                        onVoltar = {
                            navController.navigate("home") {
                                popUpTo("home") {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        },

                        viewModel = adminViewModel
                    )
                }
            } else {
                LaunchedEffect(Unit) {
                    navController.navigate("home") {
                        popUpTo("sugestoesAdmin") {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}