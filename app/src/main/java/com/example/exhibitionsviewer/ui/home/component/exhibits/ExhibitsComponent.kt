package com.example.exhibitionsviewer.ui.home.component.exhibits

import SearchView
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.R
import com.example.exhibitionsviewer.data.model.FilterData
import com.example.exhibitionsviewer.ui.home.component.exhibits.component.FilterScreen
import com.example.exhibitionsviewer.ui.home.component.exhibits.component.PublicationsGrid

@Composable
fun ExhibitsComponent(onItemClick: (Long) -> Unit){
    val viewModel: ExhibitsViewModel = viewModel(LocalContext.current as ComponentActivity)

    var filterData by remember { mutableStateOf(FilterData()) }
    val publications = viewModel.getPublications(filterData).collectAsState(null)
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    val isFilterScreenVisible = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = isFilterScreenVisible.value,
                        enter = fadeIn() + slideInHorizontally(),
                        exit = fadeOut() + slideOutHorizontally()
                    ) {
                        SearchView(
                            textState,
                            onClose = {
                                isFilterScreenVisible.value = false
                            },
                            onValueChange = {}
                        )
                    }
                        },
                actions = {
                    IconButton(
                        onClick = {
                            isFilterScreenVisible.value = !isFilterScreenVisible.value
                        }
                    ) {
                        if (isFilterScreenVisible.value) {
                            Icon(Icons.Filled.Close, contentDescription = "Закрити")
                        } else {
                            Icon(Icons.Filled.Search, contentDescription = "Пошук")
                        }
                    }
                }
            )
        },
    content = { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
                .background(
                    colorResource(id = R.color.bg_gray)
                )
        ) {

            publications.value?.let { resource ->
                PublicationsGrid(
                    content = resource,
                    onItemClick = onItemClick,
                )
            }

            AnimatedVisibility(
                visible = isFilterScreenVisible.value,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                FilterScreen(
                    onApply = {
                        isFilterScreenVisible.value = false
                        filterData = it.copy(text = textState.value.text)
                    }
                )
            }
        }
    }
)
}
