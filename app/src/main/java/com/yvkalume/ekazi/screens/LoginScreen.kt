package com.yvkalume.ekazi.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yvkalume.ekazi.R

@Composable
fun LoginScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(id = R.drawable.img_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x22FFFFFF),
                        Color(0xE40C0C0C)
                    ),
                    startY = 100.0f,
                    endY = 2100f
                )
            ), content = {})

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "eKazi",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = White
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Trouvez des oportunités de travail",
                style = MaterialTheme.typography.body2.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                border = BorderStroke(1.dp, color = White),
                onClick = {
                      navController.navigate("home")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = White
                ),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_google_logo),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Se connecter avec Google",
                            style = MaterialTheme.typography.button.copy(color = Color.Black)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}