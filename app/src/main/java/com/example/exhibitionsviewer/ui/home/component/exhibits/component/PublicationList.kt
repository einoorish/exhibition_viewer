package com.example.exhibitionsviewer.ui.home.component.exhibits.component

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.example.exhibitionsviewer.data.model.Publication
import com.google.gson.JsonObject

@Composable
fun PublicationList(publications: List<Publication>, onItemClick: (Long) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(publications) { publication ->
            PublicationItem(publication = publication, onItemClick)
        }
    }
}