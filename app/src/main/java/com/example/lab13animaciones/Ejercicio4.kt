package com.example.lab13animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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

enum class EstadoPantalla { Cargando, Contenido, Error }

@Composable
fun Ejercicio4(modifier: Modifier = Modifier) {
    var estado by remember { mutableStateOf(EstadoPantalla.Cargando) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFE0F7FA), Color(0xFFEDE7F6))
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AnimatedContent",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00838F)
        )
        Text(
            text = "Estado actual: ${estado.name}",
            fontSize = 14.sp,
            color = Color(0xFF616161)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = estado,
                transitionSpec = {
                    fadeIn(animationSpec = tween(durationMillis = 700, delayMillis = 200)) togetherWith
                            fadeOut(animationSpec = tween(durationMillis = 300))
                },
                label = "contenidoEstado"
            ) { estadoActual ->
                when (estadoActual) {
                    EstadoPantalla.Cargando -> Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = Color(0xFF00838F))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Cargando datos...",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF00838F)
                        )
                    }

                    EstadoPantalla.Contenido -> Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "✅", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "¡Datos cargados!",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                        Text(
                            text = "Todo salió bien",
                            fontSize = 14.sp,
                            color = Color(0xFF616161)
                        )
                    }

                    EstadoPantalla.Error -> Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "⚠️", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ocurrió un error",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC62828)
                        )
                        Text(
                            text = "Intenta nuevamente",
                            fontSize = 14.sp,
                            color = Color(0xFF616161)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            EstadoPantalla.values().forEach { opcion ->
                Button(
                    onClick = { estado = opcion },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (estado == opcion) Color(0xFF00838F) else Color(0xFF90A4AE)
                    )
                ) {
                    Text(opcion.name)
                }
            }
        }
    }
}