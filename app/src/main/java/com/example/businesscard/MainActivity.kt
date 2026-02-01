package com.example.businesscard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset


val GradientStart = Color(0xFF8E2DE2)
val GradientEnd = Color(0xFF2575FC)
val CardBackground = Color(0xFFFFFFFF)
val TextPrimary = Color(0xFF2D1B3D)
val TextSecondary = Color(0xFF8E7CA6)
val AccentColor = Color(0xFFA855F7)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BusinessCard()
            }
        }
    }
}

@Composable
fun BusinessCard() {
    AnimatedGradientBackground {
        GlassCard(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            ProfileImageSection()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Trần Thanh Đức",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "JUNIOR MOBILE DEVELOPER",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ContactInfoItem(Icons.Filled.Phone, "+84 905 123 456", "Mobile")
            Spacer(modifier = Modifier.height(12.dp))
            ContactInfoItem(Icons.Filled.Email, "ductran.mobile@email.com", "Work Email")
            Spacer(modifier = Modifier.height(12.dp))
            ContactInfoItem(Icons.Filled.Share, "linkedin.com/in/duckdev", "LinkedIn")
            Spacer(modifier = Modifier.height(12.dp))
            ContactInfoItem(Icons.Filled.Home, "Cam Le, Da Nang, Vietnam", "Location")
        }
    }
}

@Composable
fun ProfileImageSection() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(130.dp)
    ) {
        Box(
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
                .background(AccentColor.copy(alpha = 0.1f))
        )

        Surface(
            modifier = Modifier
                .size(120.dp)
                .border(3.dp, Color.White, CircleShape)
                .shadow(elevation = 8.dp, shape = CircleShape),
            shape = CircleShape,
            color = Color.LightGray
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Picture",
                tint = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .background(Color.Gray)
            )
        }
    }
}

@Composable
fun ContactInfoItem(icon: ImageVector, info: String, label: String) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F6FA))
            .clickable {
                // Click Event
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            color = AccentColor.copy(alpha = 0.1f),
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AccentColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = label,
                fontSize = 10.sp,
                color = TextSecondary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = info,
                fontSize = 14.sp,
                color = TextPrimary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun AnimatedGradientBackground(content: @Composable BoxScope.() -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "waterGradient")

    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 16000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offsetX"
    )

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 20000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offsetY"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        GradientStart,
                        AccentColor.copy(alpha = 0.85f),
                        GradientEnd
                    ),
                    start = Offset(offsetX, offsetY),
                    end = Offset(offsetX + 1000f, offsetY + 1200f)
                )
            ),
        content = content
    )
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.White.copy(alpha = 0.15f))
                .blur(4.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White.copy(alpha = 0.25f),
                    RoundedCornerShape(24.dp)
                )
                .border(
                    1.dp,
                    Color.White.copy(alpha = 0.35f),
                    RoundedCornerShape(24.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModernCardPreview() {
    MaterialTheme {
        BusinessCard()
    }
}
