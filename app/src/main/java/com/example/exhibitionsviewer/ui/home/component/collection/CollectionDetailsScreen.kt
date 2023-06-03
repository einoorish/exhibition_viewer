package com.example.exhibitionsviewer.ui.home.component.collection

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.ui.home.component.collection.components.GalleryScreen
import com.example.exhibitionsviewer.ui.home.component.collection.components.GuidedScreen
import com.example.exhibitionsviewer.ui.home.component.organization_details.components.gradientImageFromURL

@Composable
fun CollectionDetailsScreen(id: Long, onItemClick: (Long) -> Unit){

    val viewModel: TimelineViewModel = viewModel(LocalContext.current as ComponentActivity)

    val category: Category? by viewModel.dataFlow.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.getCategory(id)
    }

    val selectedItem = rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Check, contentDescription = "Екскурсія") },
                    label = { Text("Екскурсія") },
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Галерея") },
                    label = { Text("Галерея") },
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 }
                )
            }
        }
    ) { innerPadding ->
        Column {
            category?.let {
                gradientImageFromURL(url = it.thumbnailUrl, text = it.title, modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp))
            }
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                when (selectedItem.value) {
                    0 -> GuidedScreen(category, viewModel::isIdViewed, viewModel::setIdViewed, onItemClick)
                    1 -> GalleryScreen(category, onItemClick)
                }
            }
        }
    }
}

