package com.example.exhibitionsviewer.ui.home.component.subscribed

import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.ui.home.component.exhibits.component.PublicationsGrid

@Composable
fun SubscribedComponent(onItemClick: (Long) -> Unit){
    val viewModel: SubscriptionViewModel = viewModel(LocalContext.current as ComponentActivity)

    val subscriptions = remember { mutableStateOf(viewModel.getSubscriptions()) }

    val publications = viewModel.getAllPublications().collectAsState(null)

    if(subscriptions.value != null) {
        publications.value?.let { allPublications ->
            val subscribedPublications = allPublications.filter {
                subscriptions.value!!.map { sub -> sub.name}.contains(it.organization)
            }
            PublicationsGrid(content = subscribedPublications, onItemClick = onItemClick)
        }
    }
}
