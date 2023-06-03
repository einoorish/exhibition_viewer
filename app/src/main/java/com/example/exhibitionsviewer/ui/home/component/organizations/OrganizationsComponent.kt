package com.example.exhibitionsviewer.ui.home.component.organizations

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.R
import com.example.exhibitionsviewer.ui.home.component.organizations.components.OrganizationList

@Composable
fun OrganizationsComponent(onOrganizationSelected: (Long) -> Unit) {
    val viewModel: OrganizationsViewModel = viewModel(LocalContext.current as ComponentActivity)

    val organizations = viewModel.getAllOrganizations().collectAsState(null)

    Box(modifier = Modifier.fillMaxSize().background(color= colorResource(id = R.color.bg_gray))) {
        organizations.value?.let { resource ->
            OrganizationList(resource, onOrganizationSelected)
        }
    }
}
