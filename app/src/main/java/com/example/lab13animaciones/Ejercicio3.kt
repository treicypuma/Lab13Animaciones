package com.example.lab13animaciones

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Ejercicio3(modifier: Modifier = Modifier) {
    var movido by remember { mutableStateOf(false) }
    var offsetAlFinal by remember { mutableStateOf(false) }

    val tamano by animateDpAsState(
        targetValue = if (movido) 140.dp else 80.dp,
        animationSpec = tween(durationMillis = 800),
        label = "tamano"
    )
    val posX by animateDpAsState(
        targetValue = if (movido) 150.dp else 0.dp,
        animationSpec = tween(durationMillis = 800),
        label = "posX"
    )
    val posY by animateDpAsState(
        targetValue = if (movido) 160.dp else 0.dp,
        animationSpec = tween(durationMillis = 800),
        label = "posY"
    )

    val degradado = Brush.linearGradient(
        listOf(Color(0xFFFF7043), Color(0xFFFFB300))
    )

    // El orden de los modificadores cambia el resultado
    val modificadorCuadro = if (!offsetAlFinal) {
        Modifier
            .offset(x = posX, y = posY)
            .size(tamano)
            .clip(RoundedCornerShape(20.dp))
            .background(degradado)
    } else {
        Modifier
            .size(tamano)
            .clip(RoundedCornerShape(20.dp))
            .background(degradado)
            .offset(x = posX, y = posY)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFF3E0), Color(0xFFFFEBEE))
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tamaño y posición",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE64A19)
        )
        Text(
            text = if (offsetAlFinal) "Orden: offset al final" else "Orden: offset primero",
            fontSize = 14.sp,
            color = Color(0xFF616161)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.7f))
        ) {
            Box(
                modifier = modificadorCuadro,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cuadro",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { movido = !movido },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE64A19))
        ) {
            Text(
                text = "Mover y cambiar tamaño",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { offsetAlFinal = !offsetAlFinal },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8D6E63))
        ) {
            Text("Cambiar orden de modificadores")
        }
    }
}