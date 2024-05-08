package com.capitalone.mobile.onelist.feature.todo.standard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.capitalone.mobile.onelist.core.model.data.todo.Todo
import com.capitalone.mobile.onelist.core.ui.PreviewTheme
import com.capitalone.mobile.onelist.core.ui.components.GenericLoadingScreen
import com.capitalone.mobile.onelist.core.ui.components.OneListTopBar
import com.capitalone.mobile.onelist.feature.todo.standard.StandardViewModel.UiState.Loaded
import com.capitalone.mobile.onelist.feature.todo.standard.StandardViewModel.UiState.Loading

@Composable
fun StandardScreen(
    viewModel: StandardViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is Loading -> GenericLoadingScreen()
        is Loaded -> LoadedScreen(state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoadedScreen(
    uiState: Loaded
) {
    Scaffold(
        topBar = { OneListTopBar() }
    ) { internalPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(internalPadding)) {
            LoadedLayout(uiState)
        }
    }
}

@Composable
private fun LoadedLayout(
    uiState: Loaded
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TodoInputSection()
        TodoList(todos = uiState.todos)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TodoInputSection() {
    var inputText by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = inputText,
        onValueChange = { inputText = it },
        label = { Text("What needs to be done?") },
        trailingIcon = {
            IconButton(
                onClick = { /*TODO*/ },
                enabled = inputText.isNotBlank()
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    )
}

@Composable
private fun ColumnScope.TodoList(
    todos: List<Todo>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        itemsIndexed(todos) { index, todo ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = todo.text,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Outlined.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                    }
                }
                if (index < todos.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Preview(widthDp = 350, heightDp = 700)
@Composable
private fun Preview() {
    val todoList = mutableListOf<Todo>()
    for (i in 1..20) {
        todoList.add(
            Todo(
                id = i,
                text = "Todo $i",
            )
        )
    }

    PreviewTheme {
        LoadedScreen(Loaded(todoList))
    }
}