package com.capitalone.mobile.onelist.feature.todo.special

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
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.capitalone.mobile.onelist.feature.todo.special.SpecialViewModel.UiState.Loaded
import com.capitalone.mobile.onelist.feature.todo.special.SpecialViewModel.UiState.Loading

@Composable
fun SpecialScreen(
    viewModel: SpecialViewModel = hiltViewModel()
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
        topBar = { OneListTopBar() },
        floatingActionButton = { AddItemButton() },
        floatingActionButtonPosition = FabPosition.Center
    ) { internalPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(internalPadding)
        ) {
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
        var selectedTab by rememberSaveable { mutableStateOf(0) }
        TabRow(selectedTabIndex = selectedTab, modifier = Modifier.fillMaxWidth()) {
            Tab(selectedTab == 0, {selectedTab = 0}, text = { Text("To do") })
            Tab(selectedTab == 1, {selectedTab = 1}, text = { Text("Completed") })
        }

        if (selectedTab == 0) {
            TodoList(todos = uiState.todos)
        } else {
            CompletedList(todos = uiState.completed)
        }
    }
}

@Composable
private fun ColumnScope.TodoList(
    todos: List<Todo>,
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
                        Icon(Icons.Outlined.CheckBoxOutlineBlank, contentDescription = null)
                    }
                }
                if (index < todos.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.CompletedList(
    todos: List<Todo>,
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
                        Icon(Icons.Outlined.CheckBox, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                }
                if (index < todos.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun AddItemButton() {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        onClick = { /*TODO*/ }
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Preview(widthDp = 350, heightDp = 700)
@Composable
private fun Preview() {
    val completedList = mutableListOf<Todo>()
    for (i in 1..10) {
        completedList.add(
            Todo(
                id = i,
                text = "Todo $i",
                isCompleted = true
            )
        )
    }

    val todoList = mutableListOf<Todo>()
    for (i in 11..20) {
        todoList.add(
            Todo(
                id = i,
                text = "Todo $i",
                isCompleted = false
            )
        )
    }

    PreviewTheme {
        LoadedScreen(Loaded(todoList, completedList))
    }
}