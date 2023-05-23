package com.example.exhibitionsviewer.ui.home.component.organizations.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import com.google.gson.Gson
import com.google.gson.JsonObject

@Composable
fun OrganizationItem(organization: Organization,
                     onOrganizationSelected: (Long) -> Unit) {
    Surface(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier
            .fillMaxWidth().padding(8.dp)
            .clickable(onClick = { onOrganizationSelected(organization.id) })) {

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = organization.organizationName, style = MaterialTheme.typography.h6)

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

                // gap between icon and text
                Spacer(modifier = Modifier.width(width = 6.dp))

                Text(text = organization.physicalAddress, style = MaterialTheme.typography.body1)
            }


            Spacer(modifier = Modifier.height(8.dp))
            Text(text = organization.directions, style = MaterialTheme.typography.body1, color = Color.Blue)

            Spacer(modifier = Modifier.height(8.dp))
            // Add more content fields as needed (e.g., subject, type, mediaType)
        }
    }
}
