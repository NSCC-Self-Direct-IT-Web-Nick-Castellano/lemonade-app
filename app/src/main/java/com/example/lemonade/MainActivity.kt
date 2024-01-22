package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    val buttonImageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val lemonadeStepStringResource = when(result) {
        1 -> R.string.lemonade_step_1
        2 -> R.string.lemonade_step_2
        3 -> R.string.lemonade_step_3
        else -> R.string.lemonade_step_4
    }
    val imageDescriptionStringResource = when(result) {
        1 -> R.string.lemonade_description_1
        2 -> R.string.lemonade_description_2
        3 -> R.string.lemonade_description_3
        else -> R.string.lemonade_description_4
    }
    Column (

        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        TopHeader()
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()

        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = {
                              // behavior for when tapping on the image
                              if (result==4) {
                                  result = 1
                              } else {
                                  result++
                              }
                              },
                    shape = RoundedCornerShape(20.dp),

                    modifier = Modifier
                        .padding(0.dp)
                        .background(
                            colorResource(id = R.color.lemonade_turquoise),
                            shape = RoundedCornerShape(20.dp)
                        )


                ) {

                    LemonadeImage(
                        imageResourceId = buttonImageResource,
                        imageDescriptionResourceId = imageDescriptionStringResource
                    )

                }
                
                Text(text = stringResource(id = lemonadeStepStringResource), fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun TopHeader(
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(colorResource(id = R.color.lemonade_yellow))
            .fillMaxWidth()
            .padding(12.dp)

    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LemonadeImage(
    modifier: Modifier = Modifier,
    imageResourceId: Int,
    imageDescriptionResourceId: Int,

    ) {
    Image(
        painter = painterResource(id = imageResourceId),

        contentDescription = stringResource(
            id = imageDescriptionResourceId
        ),
        modifier = modifier
            .background(
                Color.Transparent,
//                                    colorResource(id = R.color.lemonade_turquoise),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)

    )
}

@Preview
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}