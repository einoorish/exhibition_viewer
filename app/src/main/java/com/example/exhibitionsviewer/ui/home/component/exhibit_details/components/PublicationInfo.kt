package com.example.exhibitionsviewer.ui.home.component.exhibit_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.exhibitionsviewer.data.model.Epoch
import com.example.exhibitionsviewer.data.model.ExhibitSubjects
import com.example.exhibitionsviewer.data.model.ExhibitType
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.ui.home.component.organization_details.components.imageFromURL

@Composable
fun PublicationInfo(publication: Publication?){

    publication?.let { publication ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = publication.title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text =  "#${Epoch.valueOf(publication.epoch).ukr} " +
                        "#${ExhibitSubjects.valueOf(publication.subject).ukr} " +
                        "#${ExhibitType.valueOf(publication.type).ukr}",
                style = MaterialTheme.typography.caption,
                color = Color.Blue,
                modifier = Modifier.padding(bottom = 8.dp).align(Alignment.CenterHorizontally)
            )
            imageFromURL(
                url = publication.thumbnailUrl,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .background(Color.LightGray)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = publication.description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}