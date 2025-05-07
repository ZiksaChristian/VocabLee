package com.example.vocablee.Ventanas

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocablee.BaseSQLITE.Books

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeerLibro(navController: NavController, bookId: Long) {
    val context = LocalContext.current
    val repositorio = remember { Books(context) }
    val libro by remember { mutableStateOf(repositorio.obtenerLibroPorId(bookId)) }
    val preferencias = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val fontSize = remember { mutableStateOf(preferencias.getFloat("fontSize", 18f)) }
    var rating by remember { mutableStateOf(libro?.rating ?: 0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = libro?.title ?: "Libro no encontrado", style = MaterialTheme.typography.headlineSmall) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (libro != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = libro!!.lectura,
                    fontSize = fontSize.value.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.size(40.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Califica esta Lectura:")
                    Slider(
                        value = rating.toFloat(),
                        onValueChange = { rating = it.toInt() },
                        valueRange = 0f..5f,
                        steps = 4
                    )
                }
                Button(
                    onClick = {
                        repositorio.actualizarRating(bookId, rating)
                        navController.popBackStack()
                    },
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0EC215))
                ) {
                    Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Libro no encontrado",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}