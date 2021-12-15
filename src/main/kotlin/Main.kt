import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

fun main() = Window {
    MainWindow(MainViewModel())
}

@Composable
fun MainWindow(viewModel: MainViewModel) {
    Log.d("Test", "Start")
    val state by viewModel.counterState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar {
                Log.d("Test", "TopAppBar Start")
                Text(
                    state.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    ) {
        Counter(
            state = state,
            increase = { viewModel.increase() }
        )
    }
}

data class CounterState(
    val title: String,
    val count: Int
)

@Composable
fun Counter(
    state: CounterState,
    increase: () -> Unit,
) {
    Log.d("Test", "Counter Start")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("${state.count}", style = MaterialTheme.typography.h4)
        Button(onClick = increase) {
            Text("Button")
        }
    }
}

class MainViewModel {
    private val _counterState = MutableStateFlow(CounterState(title = "Compose", count = 0))
    val counterState: StateFlow<CounterState> = _counterState

    fun increase() {
        val currentState = _counterState.value
        _counterState.value = currentState.copy(
            count = currentState.count + 1
        )
    }
}