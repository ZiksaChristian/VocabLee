package com.example.vocablee.Ventanas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocablee.Navegacion.Pantallas
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.Construction
import androidx.compose.ui.res.painterResource
import com.example.vocablee.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaOpcion(navController: NavController){
    Scaffold {
        Imagen()
        ContenidoOpcion(navController)
        Configuracion(navController)
    }
}

@Composable
fun Imagen(){
        Image(painter = painterResource(R.drawable.aprende),
            contentDescription = "Imagen tuani")
}

@Composable
fun ContenidoOpcion(navController: NavController) {
    //Spacer(Modifier.height(20.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lee y mejora tu léxico",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .background(Color(0x99000000), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.size(40.dp))
        Button(
            onClick = {
                navController.navigate(route = Pantallas.ventana2.route)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Empieza a leer",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun Configuracion(navController: NavController){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 40.dp)
        ,verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End){
        Button(
            onClick = {
                navController.navigate(route = Pantallas.ventana3.route)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEC447)),
            modifier = Modifier
                .width(80.dp)
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(imageVector = Icons.Rounded.Brush, contentDescription = null)
        }
    }

}