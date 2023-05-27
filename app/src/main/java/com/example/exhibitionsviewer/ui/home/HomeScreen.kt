package com.example.exhibitionsviewer.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.exhibitionsviewer.ui.home.component.organizations.OrganizationsComponent
import com.example.exhibitionsviewer.ui.home.component.exhibits.ExhibitsComponent
import com.example.exhibitionsviewer.ui.home.component.subscribed.SubscribedComponent

@Composable
fun HomeScreen(
    onPublicationSelected: (Long) -> Unit,
    onOrganizationSelected: (Long) -> Unit,
) {
    val selectedItem = rememberSaveable { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Exhibits") },
                    label = { Text("Exhibits") },
                    selected = selectedItem.value == 0,
                    onClick = { selectedItem.value = 0 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Organizations") },
                    label = { Text("Organizations") },
                    selected = selectedItem.value == 1,
                    onClick = { selectedItem.value = 1 }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Subscribed") },
                    label = { Text("Subscribed") },
                    selected = selectedItem.value == 2,
                    onClick = { selectedItem.value = 2 }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (selectedItem.value) {
                0 -> ExhibitsComponent(onPublicationSelected)
                1 -> OrganizationsComponent(onOrganizationSelected)
                2 -> SubscribedComponent(onPublicationSelected)
            }
        }
    }
}