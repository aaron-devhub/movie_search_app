package com.example.cineflowtickets

import android.content.Intent
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cineflowtickets.ui.theme.CineFlowTicketsTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.ui.platform.LocalContext
import com.example.cineflowtickets.ui.theme.BackGroundColor
import kotlin.jvm.java
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.example.cineflowtickets.ui.theme.CardColor
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.indication
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineFlowTicketsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PantallaPrincipal()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PantallaPrincipal() {
    val context = LocalContext.current
    var valor = rememberSaveable() { mutableStateOf("") }
    val titulo = listOf("La Bola Negra", "Ice Age", "El club de la lucha")
    val imagenes: List<Int> = listOf(R.drawable.ic_poster_bola_negra, R.drawable.ic_poster_ice_age, R.drawable.ic_poster_club_lucha)

    Column(modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(16.dp)
            .systemBarsPadding()
    ) {
        Text(
            text = stringResource(R.string.main_title),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = valor.value,
            onValueChange = { valor.value = it },
            label = {Text(stringResource(R.string.search_label))}
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            items(3){
                indice ->
                ElevatedCard(colors = CardDefaults.elevatedCardColors(containerColor = CardColor)) {
                    Column(){
                        Image(painter = painterResource(imagenes[indice]),
                            contentDescription = null,
                            modifier = Modifier
                                .height(170.dp)
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            contentScale = ContentScale.Fit)
                        Text(titulo[indice], style = MaterialTheme.typography.headlineLarge, fontSize = 15.sp,
                            color = Color.White)
                        OutlinedButton(modifier = Modifier.padding(bottom = 10.dp),
                            onClick = {
                            if(valor.value.isBlank()){
                                Toast.makeText(context, R.string.err_empty, Toast.LENGTH_SHORT).show()
                            } else {
                                Intent(context, DetailActivity::class.java).apply {
                                    putExtra("movie", valor.value)
                                    context.startActivity(this)
                                }
                            }
                        }) {
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Icon(imageVector = Icons.Default.ConfirmationNumber, contentDescription = null, tint = Color.Red)
                                Spacer(modifier = Modifier.width(3.dp))
                                Text("Reservar entradas", color = Color.Red)
                            }
                        }
                    }
                }
            }

        }
    }
}
