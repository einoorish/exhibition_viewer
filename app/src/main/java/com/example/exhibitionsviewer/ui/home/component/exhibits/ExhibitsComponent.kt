package com.example.exhibitionsviewer.ui.home.component.exhibits

import SearchView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.ui.home.component.exhibits.component.PublicationsGrid

@Composable
fun ExhibitsComponent(onItemClick: (Long) -> Unit){
    val viewModel: ExhibitsViewModel = viewModel(LocalContext.current as ComponentActivity)

    val publications = viewModel.getAllPublications().collectAsState(null)

    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = { SearchView(textState) },
    ) { padding ->
        publications.value?.let { resource ->
            PublicationsGrid(content = resource, onItemClick = onItemClick, modifier = Modifier.padding(top = padding.calculateTopPadding()))
        }
    }
}
