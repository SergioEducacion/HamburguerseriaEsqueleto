package com.example.hamburguerseriaesqueleto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hamburguerseriaesqueleto.ui.PantallaInicial
import com.example.hamburguerseriaesqueleto.ui.PantallaPedidoActual
import com.example.hamburguerseriaesqueleto.ui.PantallaPedidosHistoricos
import com.example.hamburguerseriaesqueleto.ui.theme.HamburguerseriaEsqueletoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HamburguerseriaEsqueletoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HamburguerseriaEsqueletoTheme {
        Greeting("Android")
    }
}

enum class PrincipalScreen(@StringRes val title: Int) {
    PantallaPrincipal(title = R.string.p1),
    PantallaPedidoActual(title = R.string.p2),
    PantallaHistoricoPedidos(title = R.string.p3),
}

@Composable
fun Principal(navController: NavHostController = rememberNavController()) {
   // val viewModelApostar: ApostarViewModel = viewModel()
   // viewModelApostar.iniciarlizarValores();
   // val uiState by viewModelApostar.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = PrincipalScreen.PantallaPrincipal.name,
        //modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = PrincipalScreen.PantallaPrincipal.name) {
            PantallaInicial(
                onClickCambiarPantallaPedidoActual = { navController.navigate(PrincipalScreen.PantallaPedidoActual.name) },
                onClickCambiarPantallaPedidoHistorico = { navController.navigate(PrincipalScreen.PantallaPedidoActual.name) })

        }
        composable(route = PrincipalScreen.PantallaPedidoActual.name) {
            PantallaPedidoActual { navController.navigate(PrincipalScreen.PantallaPrincipal.name) }
        }
        composable(route = PrincipalScreen.PantallaHistoricoPedidos.name) {
            PantallaPedidosHistoricos { navController.navigate(PrincipalScreen.PantallaPrincipal.name) }
        }
    }
}

