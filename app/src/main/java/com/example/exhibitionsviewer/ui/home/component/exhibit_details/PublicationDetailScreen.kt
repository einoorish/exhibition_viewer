package com.example.exhibitionsviewer.ui.home.component.exhibit_details

import com.example.exhibitionsviewer.ui.home.component.exhibit_details.components.PublicationInfo
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.ui.home.component.exhibit_details.components.PublicationMedia

@Composable
fun PublicationDetailScreen(id: Long) {

    val viewModel: ExhibitDetailsViewModel = viewModel(LocalContext.current as ComponentActivity)

    val publication: State<Publication?> = viewModel.getPublication(id).collectAsState(null)

    val selectedItem = rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Exhibits") },
                    label = { Text("Information") },
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Media") },
                    label = { Text("Media") },
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (selectedItem.value) {
                0 -> PublicationInfo(publication.value)
                1 -> PublicationMedia(viewModel.getFileRequestUrl(publication.value))
            }
        }
    }
}