package com.example.cineflowtickets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cineflowtickets.ui.theme.CineFlowTicketsTheme
import com.example.cineflowtickets.ui.theme.Purple80
import java.util.Calendar

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name: String = intent.getStringExtra("movie") ?: ""
        enableEdgeToEdge()
        setContent {
            CineFlowTicketsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PantallaSecundaria(name)
                }
            }
        }
    }
}

@Composable
fun PantallaSecundaria(name: String){
    val context = LocalContext.current
    var mostrarPicker = rememberSaveable() { mutableStateOf(false) }
    var fecha = rememberSaveable() { mutableStateOf("No seleccionada") }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(name, fontSize = 25.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Button(colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White),
            onClick = {mostrarPicker.value = true}
        ) {
            Text(stringResource(R.string.dialog_title))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Fecha: ${fecha.value}", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=trailer $name"))
            context.startActivity(intent)
        }) {
            Text("Ver Tráiler", color = Color.Red)
        }
    }

    if (mostrarPicker.value) {

        val calendario = Calendar.getInstance()

        android.app.DatePickerDialog(
            context,
            { _, year, month, day ->

                fecha.value = "$day/${month + 1}/$year"
                mostrarPicker.value = false

            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}

