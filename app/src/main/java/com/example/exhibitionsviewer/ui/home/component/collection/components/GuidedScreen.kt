package com.example.exhibitionsviewer.ui.home.component.collection.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exhibitionsviewer.data.model.Category
import kotlin.reflect.KFunction1

@Composable
fun GuidedScreen(
    category: Category?,
    isIdViewed: KFunction1<Long, Boolean>,
    setIdViewed: KFunction1<Long, Unit>,
    onItemClick: (Long) -> Unit){

    val listState = rememberLazyListState()
    category?.let { c ->
        val items = c.objects
        var foundNotViewed = false

        items.forEach { item ->
            item.wasViewed = isIdViewed(item.id)
            if (!item.wasViewed && !foundNotViewed) {
                foundNotViewed = true
                item.current = true
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            items(items = items) { item ->
                TimelineItem(item) {
                    setIdViewed(item.id)
                    onItemClick(item.id)
                }
            }
        }
    }
}

