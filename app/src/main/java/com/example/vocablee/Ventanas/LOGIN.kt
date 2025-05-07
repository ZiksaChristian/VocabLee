package com.example.vocablee.Ventanas

import androidx.compose.ui.Alignment
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vocablee.Navegacion.Pantallas
import com.example.vocablee.BaseSQLITE.InteraccionBD

@Composable
fun PLOGIN(navController: NavController) {
    var usuariobd by remember { mutableStateOf("") }
    var contrabd by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userDAO = InteraccionBD(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("LOGIN", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(60.dp))

        OutlinedTextField(
            value = usuariobd,
            onValueChange = { usuariobd = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrabd,
            onValueChange = { contrabd = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (userDAO.checkUser(usuariobd, contrabd)) {
                    navController.navigate(Pantallas.ventana1.route)
                } else {
                    Toast.makeText(context, "Usuario/Contraseña Incorrecto", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(Pantallas.registro.route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2DA0D5))
        ) {
            Text("Registrar")
        }
    }
}
