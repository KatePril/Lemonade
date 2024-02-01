package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeApp() {
    AppStep(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun AppStep(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = Color(red = 206, green = 248, blue = 217, alpha = 255)
        ) {
            Header()
            when (step) {
                1 -> {
                    LemonadeWithButtonAndImage(
                        imageId = R.drawable.lemon_tree,
                        titleId = R.string.img_title_one,
                        textId = R.string.msg_step_one,
                        onImageCLick = {
                            step = 2
                            squeezeCount = (2..6).random()
                        }
                    )
                }
                2 -> {
                    LemonadeWithButtonAndImage(
                        imageId = R.drawable.lemon_squeeze,
                        titleId = R.string.img_title_two,
                        textId = R.string.msg_step_two,
                        onImageCLick = {
                            squeezeCount--
                            if (squeezeCount == 0) step = 3
                        }
                    )
                }
                3 -> {
                    LemonadeWithButtonAndImage(
                        imageId = R.drawable.lemon_drink,
                        titleId = R.string.img_title_three,
                        textId = R.string.msg_step_three,
                        onImageCLick = { step = 4 }
                    )
                }
                4 -> {
                    LemonadeWithButtonAndImage(
                        imageId = R.drawable.lemon_restart,
                        titleId = R.string.img_title_four,
                        textId = R.string.msg_step_four,
                        onImageCLick = { step = 1 }
                    )
                }
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            color = Color(red = 255, green = 180, blue = 41),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Lemonade",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }

    }
}

@Composable
fun LemonadeWithButtonAndImage(
    imageId: Int,
    titleId: Int,
    textId: Int,
    onImageCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onImageCLick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 70, green = 216, blue = 140, alpha = 255)
            )
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(titleId)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textId),
            fontSize = 18.sp
        )
    }
}



