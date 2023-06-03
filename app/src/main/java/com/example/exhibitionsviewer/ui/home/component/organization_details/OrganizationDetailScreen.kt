package com.example.exhibitionsviewer.ui.home.component.organization_details

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exhibitionsviewer.R
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.OrgType
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.ui.home.component.organization_details.components.imageFromURL
import com.example.exhibitionsviewer.ui.home.component.subscribed.SubscriptionViewModel
import com.example.exhibitionsviewer.ui.theme.blue500

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrganizationDetailScreen(id: Long, onCollectionSelected: (Long) -> Unit) {
    val context = LocalContext.current
    val viewModel: OrganizationViewModel = viewModel(context as ComponentActivity)
    val subscriptionViewModel: SubscriptionViewModel = viewModel(LocalContext.current as ComponentActivity)

    val organization: State<Organization?> = viewModel.getOrganization(id).collectAsState(null)
    val isSubscribed = remember { mutableStateOf(subscriptionViewModel.isSubscribed(id)) }

    val items: List<Category>? by viewModel.dataFlow.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.getCategoriesForOrganization(id)
    }

    val pagerState = remember { mutableStateOf(PagerState(initialPage = 0)) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = if(!isSubscribed.value) {"Підписатись"} else {"Відписатись"}) },
                backgroundColor = blue500,
                onClick = { organization.value?.let {
                    if(!isSubscribed.value) {
                        Toast.makeText(context, "Оформлено підписку", Toast.LENGTH_LONG).show()
                        subscriptionViewModel.subscribe(it.id, it.organizationName)
                    } else {
                        Toast.makeText(context, "Підписку скасовано", Toast.LENGTH_LONG).show()
                        subscriptionViewModel.unsubscribe(it.id, it.organizationName)
                    }
                    isSubscribed.value = !isSubscribed.value
                } }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.bg_gray))
                    .padding(16.dp)
            ) {
                organization.value?.organizationName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                organization.value?.let {
                    Text(
                        text = OrgType.valueOf(it.type).ukr,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.DarkGray
                    )
                }

                organization.value?.let {
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

                        Text(
                            text = it.physicalAddress,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
                organization.value?.let {
                    Text(
                        text = it.directions,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(8.dp),
                        color = Color.Blue
                    )
                }

                Text(
                    text = "Колекції",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(10.dp)

                )

                var sizeImage by remember { mutableStateOf(IntSize.Zero) }

                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = sizeImage.height.toFloat()/1.5f,  // 1/3
                    endY = sizeImage.height.toFloat()
                )

                items?.let {
                    VerticalPager(
                        pageCount = it.size,
                        pageSize = PageSize.Fixed(210.dp),
                        modifier = Modifier.fillMaxHeight()
                    ) { page ->
                        Card(
                            elevation = 4.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable(onClick = { onCollectionSelected(it[page].id) })
                        ) {
                            imageFromURL(
                                url = it[page].thumbnailUrl,
                                modifier = Modifier.onGloballyPositioned { coords -> sizeImage = coords.size }
                            )
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .background(gradient))
                            Text(
                                it[page].title,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp, 160.dp, 10.dp, 10.dp),
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    )
}