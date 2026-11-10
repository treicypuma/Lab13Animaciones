package com.example.lab13animaciones

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun Ejercicio2(modifier: Modifier = Modifier) {
    var esVerde by remember { mutableStateOf(false) }
    var usarSpring by remember { mutableStateOf(false) }

    val especificacion: AnimationSpec<Color> = if (usarSpring) {
        spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    } else {
        tween(durationMillis = 900)
    }

    val colorCuadro by animateColorAsState(
        targetValue = if (esVerde) Color(0xFF43A047) else Color(0xFF1E88E5),
        animationSpec = especificacion,
        label = "colorCuadro"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFE3F2FD), Color(0xFFE8F5E9))
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "animateColorAsState",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0)
        )
        Text(
            text = "Animación actual: ${if (usarSpring) "spring" else "tween"}",
            fontSize = 14.sp,
            color = Color(0xFF616161)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(colorCuadro),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (esVerde) "Verde" else "Azul",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { esVerde = !esVerde },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
        ) {
            Text(
                text = "Cambiar color",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = { usarSpring = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!usarSpring) Color(0xFF1565C0) else Color(0xFF90A4AE)
                )
            ) { Text("tween") }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = { usarSpring = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (usarSpring) Color(0xFF1565C0) else Color(0xFF90A4AE)
                )
            ) { Text("spring") }
        }
    }
}