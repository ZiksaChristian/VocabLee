@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vocablee.Ventanas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vocablee.BaseSQLITE.Book
import com.example.vocablee.BaseSQLITE.Books
import com.example.vocablee.Navegacion.Pantallas
import androidx.compose.ui.graphics.Color


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaLeer(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de libros", style = MaterialTheme.typography.headlineSmall) }
            )
        }
    ) { innerPadding ->
        val context = LocalContext.current
        val booksRepository = remember { Books(context) }
        val libros = remember { booksRepository.obtenerTodosLosLibros() }

        if (libros.isEmpty()) {
            Text(
                text = "No hay libros disponibles",
                fontSize = 20.sp,
                modifier = Modifier.fillMaxSize().padding(16.dp)
            )
        } else {
            ContenidoLectura(
                libros = libros,
                navController = navController,
                onRatingChanged = { bookId, rating ->
                    booksRepository.actualizarRating(bookId, rating)
                    booksRepository.obtenerTodosLosLibros()
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ContenidoLectura(libros: List<Book>, navController: NavController, onRatingChanged: (Long, Int) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(libros) { libro ->
            LibroItem(libro = libro, navController = navController, onRatingChanged = onRatingChanged)
        }
    }
}

@Composable
fun LibroItem(libro: Book, navController: NavController, onRatingChanged: (Long, Int) -> Unit) {
    val backgroundColor = when {
        libro.rating >= 5 -> Color(0xFF4CAF50) // Verde oscuro
        libro.rating in 3..4 -> Color(0xFFFFC107) // Amarillo
        libro.rating in 1..2 -> Color(0xFFF44336) // Rojo
        else -> Color.Transparent // Color blanco si no hay calificación
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                navController.navigate(route = Pantallas.Leer(libro.id).route)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = libro.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                if (libro.rating > 0) {
                    Text(
                        text = "Calificación: ${libro.rating}",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}