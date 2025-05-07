package com.example.vocablee.Navegacion

sealed class Pantallas (val route:String){
    object ventana1:Pantallas("pantalla_Opcion")
    object ventana2:Pantallas("pantalla_Lectura")
    object ventana3:Pantallas("pantalla_Config")
    object login:Pantallas("pantalla_LOGIN")
    object registro:Pantallas("pantalla_REGISTRO")
    data class Leer(val bookId: Long) : Pantallas("pantalla_Leer/$bookId")
}