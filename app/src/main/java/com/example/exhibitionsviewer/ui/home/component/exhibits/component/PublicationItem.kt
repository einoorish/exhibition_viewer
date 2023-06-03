package com.example.exhibitionsviewer.ui.home.component.exhibits.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.ui.home.component.organization_details.components.imageFromURL
import com.google.gson.Gson
import com.google.gson.JsonObject

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PublicationItem(publication: Publication, onItemClick: (Long) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = { onItemClick(publication.id) }),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            imageFromURL(url = publication.thumbnailUrl, Modifier.fillMaxWidth().requiredHeightIn(min = 50.dp, max = 300.dp))
            Text(text = publication.title, style = MaterialTheme.typography.body1, modifier = Modifier.padding(top = 8.dp))
            publication.organization?.let { Text(text = it, style = MaterialTheme.typography.caption, modifier = Modifier.padding(top = 4.dp)) }
        }
    }
}
