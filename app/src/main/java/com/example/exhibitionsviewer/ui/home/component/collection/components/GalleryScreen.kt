package com.example.exhibitionsviewer.ui.home.component.collection.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.ui.home.component.exhibits.component.PublicationItem

@Composable
fun GalleryScreen(category: Category?, onItemClick: (Long) -> Unit){
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8F8))
            .padding(7.dp),
        state = listState
    ) {
        if (category != null) {
            items(category.objects) { publication ->
                PublicationItem(publication = publication, onItemClick)
            }
        }
    }
}

