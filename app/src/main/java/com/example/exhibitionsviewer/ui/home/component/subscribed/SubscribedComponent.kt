package com.example.exhibitionsviewer.ui.home.component.subscribed

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.R
import com.example.exhibitionsviewer.ui.home.component.exhibits.component.PublicationsGrid

@Composable
fun SubscribedComponent(onItemClick: (Long) -> Unit){
    val viewModel: SubscriptionViewModel = viewModel(LocalContext.current as ComponentActivity)

    val subscriptions = remember { mutableStateOf(viewModel.getSubscriptions()) }

    val publications = viewModel.getAllPublications().collectAsState(null)

    Box(modifier = Modifier.fillMaxSize().background(color=colorResource(id = R.color.bg_gray))) {
        if(subscriptions.value != null) {
            publications.value?.let { allPublications ->
                val subscribedPublications = allPublications.filter {
                    subscriptions.value!!.map { sub -> sub.name}.contains(it.organization)
                }
                PublicationsGrid(
                    content = subscribedPublications,
                    onItemClick = onItemClick
                )
            }
        }
    }
}
