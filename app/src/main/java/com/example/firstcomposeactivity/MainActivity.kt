package com.example.firstcomposeactivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeactivity.ui.theme.FirstComposeActivityTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApp {
//                MyScreenContent()
//            }
            ExampleCard()
        }
    }
}

@Preview("Priority Preview")
@Composable
fun DefaultPreview() {
    ExampleCard()
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    FirstComposeActivityTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun ExampleCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { /* do nothin */ } )
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(45.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ){
        // image goes here
        }
        Column (
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
                ){
            Text(text = "Jordan Flammia", fontWeight = FontWeight.Bold)
            // LocalContentAlpha is defining opacity level of its children
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backGroundColor by animateColorAsState(if(isSelected) Color.Magenta else Color.Transparent)
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backGroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}
