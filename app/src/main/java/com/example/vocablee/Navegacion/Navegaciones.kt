package com.example.vocablee.Navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vocablee.Ventanas.LeerLibro
import com.example.vocablee.Ventanas.PLOGIN
import com.example.vocablee.Ventanas.PREGISTRO
import com.example.vocablee.Ventanas.PantallaConfig
import com.example.vocablee.Ventanas.PantallaLeer
import com.example.vocablee.Ventanas.PantallaOpcion


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Pantallas.login.route){
        composable(route = Pantallas.ventana1.route){
            PantallaOpcion(navController)
        }
        composable(route = Pantallas.ventana2.route){
            PantallaLeer(navController)
        }
        composable(route = Pantallas.ventana3.route){
            PantallaConfig(navController)
        }
        composable(route = Pantallas.login.route){
            PLOGIN(navController)
        }
        composable(route = Pantallas.registro.route){
            PREGISTRO(navController)
        }
        composable(
            route = "pantalla_Leer/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getLong("bookId") ?: return@composable
            LeerLibro(navController, bookId)
        }
    }
}