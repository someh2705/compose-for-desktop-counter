import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

fun main() = Window {
    MainWindow(rememberMainWindowState())
}

@Composable
fun MainWindow(state: MainWindowState) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    "Compose Demo Home Page",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    ) {
        val count by state.count.collectAsState()

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("You have pushed the button this many times:")
                Text("$count", style = MaterialTheme.typography.h4)
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            IconButton(
                modifier = Modifier.padding(end = 30.dp, bottom = 30.dp).size(60.dp).clip(CircleShape)
                    .background(color = MaterialTheme.colors.primary),
                onClick = state::increase
            ) {
                Icon(Icons.Sharp.Add, "Plus", tint = Color.White)
            }
        }
    }
}

@Composable
fun rememberMainWindowState() = remember {
    MainWindowState()
}

class MainWindowState {
    private val _count = MutableStateFlow(
        0
    )
    val count: StateFlow<Int> = _count

    fun increase() {
        _count.value++
    }
}