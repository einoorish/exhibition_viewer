package com.example.exhibitionsviewer.ui.home.component.organizations

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.ui.home.component.organizations.components.OrganizationList

@Composable
fun OrganizationsComponent(onOrganizationSelected: (Long) -> Unit) {
    val viewModel: OrganizationsViewModel = viewModel(LocalContext.current as ComponentActivity)

    val organizations = viewModel.getAllOrganizations().collectAsState(null)

    organizations.value?.let { resource ->
        OrganizationList(resource, onOrganizationSelected)
    }
}
