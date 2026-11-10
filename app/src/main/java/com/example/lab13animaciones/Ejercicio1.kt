package com.example.lab13animaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun Ejercicio1(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFE8EAF6), Color(0xFFF3E5F5))
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AnimatedVisibility",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5)
        )
        Text(
            text = "Toca el botón para mostrar u ocultar",
            fontSize = 14.sp,
            color = Color(0xFF616161)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { visible = !visible },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
        ) {
            Text(
                text = if (visible) "Ocultar" else "Mostrar",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(700)) +
                    scaleIn(initialScale = 0.8f, animationSpec = tween(700)),
            exit = fadeOut(animationSpec = tween(700)) +
                    scaleOut(targetScale = 0.8f, animationSpec = tween(700))
        ) {
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF5C6BC0), Color(0xFFAB47BC))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "¡Hola! 👋",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}