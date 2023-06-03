package com.example.exhibitionsviewer.ui.home.component.organizations.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.exhibitionsviewer.R
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import com.google.gson.Gson
import com.google.gson.JsonObject

@Composable
fun OrganizationList(organizations: List<Organization>,
                     onOrganizationSelected: (Long) -> Unit) {

    LazyColumn(modifier = Modifier.fillMaxSize()
    ) {
        items(organizations) { organization ->
            OrganizationItem(
                organization = organization,
                onOrganizationSelected = onOrganizationSelected
            )
        }
    }
}