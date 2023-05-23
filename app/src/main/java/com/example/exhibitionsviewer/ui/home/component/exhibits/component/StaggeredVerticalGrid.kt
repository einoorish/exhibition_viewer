package com.example.exhibitionsviewer.ui.home.component.exhibits.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.exhibitionsviewer.data.model.Publication
import kotlin.math.ceil

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PublicationsGrid(
    content: List<Publication>,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
    ) {
        items(content.size) { item ->  PublicationItem(publication = content[item], onItemClick = onItemClick) }
    }
}