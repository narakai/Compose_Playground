package com.leon.compose_playground

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leon.compose_playground.ui.theme.Compose_PlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_PlaygroundTheme {
                Box(modifier = Modifier.fillMaxSize()) {
//                    MySurface(Modifier.align(Alignment.Center))
//                    CardDemo()
//                    AlertDialogSample()
//                    DropdownDemo()
//                    LinearProgressIndicatorSample()
//                    CircularProgressIndicatorSample()
//                    CompositionLocalDemo()
                    CompositionLocalDemo2()
                }
            }
        }
    }
}

@Composable
fun CheckBoxDemo() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

@Composable
fun RadioButtonSample() {
    val radioOptions = listOf("A", "B", "C")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SwitchDemo() {
    val checkedState = remember { mutableStateOf(true) }
    Switch(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

@Composable
fun MySliderDemo() {
    var sliderPosition by remember { mutableStateOf(0f) }
    Text(text = sliderPosition.toString())
    Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
}

@Composable
fun TextFieldDemo() {
    Column(Modifier.padding(16.dp)) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it }
        )
        Text("The textfield has this text: " + textState.value.text)
    }
}

@Composable
fun SnackbarDemo() {
    Column {
        val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

        Button(onClick = { setSnackBarState(!snackbarVisibleState) }) {
            if (snackbarVisibleState) {
                Text("Hide Snackbar")
            } else {
                Text("Show Snackbar")
            }
        }
        if (snackbarVisibleState) {
            Snackbar(
                action = {
                    Button(onClick = {}) {
                        Text("MyAction")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "This is a snackbar!") }
        }
    }
}

@Composable
fun MySurface(modifier: Modifier) {
    Surface(
        modifier = modifier.size(300.dp),
        color = Color.Gray,
        contentColor = colorResource(id = R.color.purple_700),
        elevation = 1.dp,
        border = BorderStroke(1.dp, Color.Black),
    ) {
        MyColumn()
    }
}

@Composable
fun MyColumn() {
    val list = listOf("0", "1", "2")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        list.forEach { _ ->
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 22.sp
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun BoxExample() {
    Box(Modifier.fillMaxSize()) {
        Text("This text is drawn first", modifier = Modifier.align(Alignment.TopCenter))
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight()
                .width(
                    50.dp
                )
                .background(Color.Blue)
        )
        Text("This text is drawn last", modifier = Modifier.align(Alignment.Center))
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            onClick = {}
        ) {
            Text("+")
        }
    }
}

@Composable
fun CardDemo() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(buildAnnotatedString {
                append("Welcome to ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                ) {
                    append("Jetpack Compose Playground")
                }
            })

            Text(buildAnnotatedString {
                append("Now you are in the ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.W900)
                ) {
                    append("Card")
                }
                append(" section")
            })
        }
    }
}

@Composable
fun AlertDialogSample() {
    MaterialTheme {
        Column {
            val isOpenDialog = remember {
                mutableStateOf(false)
            }

            Button(onClick = { isOpenDialog.value = true }) {
                Text(text = "Click Me")
            }

            if (isOpenDialog.value) {
                AlertDialog(
                    onDismissRequest = { isOpenDialog.value = false },
                    title = {
                        Text(text = "Title")
                    },
                    text = {
                        Text(text = "desc")
                    },
                    confirmButton = {
                        Button(onClick = { isOpenDialog.value = true }) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { isOpenDialog.value = false }) {
                            Text(text = "Dismiss")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(
                    Color.Gray
                )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Red
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}

@Composable
fun LinearProgressIndicatorSample() {
    var progress by remember { mutableStateOf(0.1f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(Modifier.height(30.dp))
        Text("LinearProgressIndicator with undefined progress")
        LinearProgressIndicator()
        Spacer(Modifier.height(30.dp))
        Text("LinearProgressIndicator with progress set by buttons")
        LinearProgressIndicator(progress = animatedProgress)
        Spacer(Modifier.height(30.dp))
        OutlinedButton(
            onClick = {
                if (progress < 1f) progress += 0.1f
            }
        ) {
            Text("Increase")
        }

        OutlinedButton(
            onClick = {
                if (progress > 0f) progress -= 0.1f
            }
        ) {
            Text("Decrease")
        }
    }
}

@Composable
fun CircularProgressIndicatorSample() {
    var progress by remember { mutableStateOf(0.1f) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(30.dp))
        Text("CircularProgressIndicator with undefined progress")
        CircularProgressIndicator()
        Spacer(Modifier.height(30.dp))
        Text("CircularProgressIndicator with progress set by buttons")
        CircularProgressIndicator(progress = animatedProgress)
        Spacer(Modifier.height(30.dp))
        OutlinedButton(
            onClick = {
                if (progress < 1f) progress += 0.1f
            }
        ) {
            Text("Increase")
        }

        OutlinedButton(
            onClick = {
                if (progress > 0f) progress -= 0.1f
            }
        ) {
            Text("Decrease")
        }
    }
}

var isStatic = true
var compositionLocalName = ""
val currentLocalColor = if (isStatic) {
    compositionLocalName = "StaticCompositionLocal 场景"
    staticCompositionLocalOf { Color.Black }
} else {
    compositionLocalName = "DynamicCompositionLocal 场景"
    compositionLocalOf { Color.Black }
}

var recomposeFlag = "Init"

@Preview
@Composable
fun CompositionLocalDemo(isStatic: Boolean = false) {
    var color by remember { mutableStateOf(Color.Green) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${compositionLocalName}")
            Spacer(Modifier.height(20.dp))
            CompositionLocalProvider(
                currentLocalColor provides color
            ) {
                TaggedBox("Wrapper: ${recomposeFlag}", 400.dp, Color.Red) {
                    TaggedBox("Middle: ${recomposeFlag}", 300.dp, currentLocalColor.current) {
                        TaggedBox("Inner: ${recomposeFlag}", 200.dp, Color.Yellow)
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    color = Color.Blue
                }
            ) {
                Text(text = "Change Theme")
            }
        }
    }
    recomposeFlag = "Recompose"
}

@Composable
fun TaggedBox(tag: String, size: Dp, background: Color, content: @Composable () -> Unit = {}) {
    Column(
        modifier = Modifier
            .size(size)
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = tag)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

var LocalString = compositionLocalOf { "Jetpack Compose" }

@Composable
fun CompositionLocalDemo2() {
    Column {
        CompositionLocalProvider(
            LocalString provides "Hello World"
        ) {
            Text(
                text = LocalString.current,
                color = Color.Green
            )
            CompositionLocalProvider(
                LocalString provides "Ruger McCarthy"
            ) {
                Text(
                    text = LocalString.current,
                    color = Color.Blue
                )
            }
        }
        Text(
            text = LocalString.current,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_PlaygroundTheme {
        Greeting("Android")
    }
}