@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vocablee.Ventanas

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vocablee.BaseSQLITE.InteraccionBD
import com.example.vocablee.Navegacion.Pantallas

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaConfig(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajustar tamaño de fuente",
                    style = MaterialTheme.typography.headlineSmall) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        ContenidoConfiguracion(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContenidoConfiguracion(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val usuarios = InteraccionBD(context)
    val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val fontTamaño = rememberSaveable { mutableStateOf(sharedPreferences.getFloat("fontSize", 18f)) }

        Column (modifier = Modifier
            .padding(vertical = 80.dp, horizontal = 10.dp)
            .padding(start = 20.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Spacer(modifier = Modifier.size(100.dp))
            Slider(
                value = fontTamaño.value,
                onValueChange = { newSize ->
                    fontTamaño.value = newSize
                    with(sharedPreferences.edit()) {
                        putFloat("fontSize", newSize)
                        apply()
                    }
                },
                valueRange = 12f..30f,
                steps = 18
            )
            Text("Tamaño de fuente: ${fontTamaño.value.toInt()}",
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.size(120.dp))

            Button(onClick = {
                navController.navigate(route = Pantallas.ventana1.route)
            }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0EC215))) {
                Text("Guardar")
            }

            Spacer(modifier = Modifier.size(120.dp))
            Button(
                onClick = {
                    usuarios.formateo()
                    Toast.makeText(context, "Usuarios Eliminados", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDD1919))) {
                Text("Eliminar Guardado")
            }
        }
}