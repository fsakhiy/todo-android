package com.fairuzsakhiy.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fairuzsakhiy.todo.ui.theme.ToDoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoApps()
                }
            }
        }
    }
}

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Inter")
val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

@Composable
fun ToDoApps() {
    var inputValue by remember {
        mutableStateOf("")
    }

    var count by remember {
        mutableStateOf(0)
    }

    var toDoList = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFeeeeee))

    ) {
        Header()
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            OutlinedTextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                label = { Text("enter something in mind...")},
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontFamily = fontFamily,),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Button(onClick = {  if(inputValue != "") toDoList.add(inputValue)  },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text("Add")
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            text = stringResource(id = R.string.todo_title),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        LazyColumn (
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(toDoList) {
                item ->
                ToDoCard(toDo = item, deleteFn = { toDoList.remove(item) })
            }
        }
    }
}

@Composable
fun ToDoCard(toDo: String, deleteFn: (String) -> Unit) {
    Row(
        modifier = Modifier
//            .fillMaxWidth()
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
//            .border(shape = RoundedCornerShape(20), border = BorderStroke(1.dp, Color.Black))
            .background(Color(0xFF6650a4)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.9f)
                ,
                text = toDo,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Color.White
            )
        }
        IconButton(onClick = { deleteFn(toDo) }, enabled = true) {
            Icon(Icons.Outlined.Delete, contentDescription = null, tint = Color.White)
        }
    }
}
@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier
//            .background(Color(0xFF40064a))
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp)
        ,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = R.string.header),
            fontSize = 24.sp,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF40064a)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoTheme {
    }
}