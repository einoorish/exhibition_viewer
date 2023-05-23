package com.example.exhibitionsviewer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exhibitionsviewer.ui.theme.MyAppTheme
import com.example.exhibitionsviewer.ui.home.HomeScreen
import com.example.exhibitionsviewer.ui.home.component.collection.CollectionDetailsScreen
import com.example.exhibitionsviewer.ui.home.component.exhibit_details.PublicationDetailScreen
import com.example.exhibitionsviewer.ui.home.component.organization_details.OrganizationDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(
                            onExhibitSelected = { id -> navController.navigate("exhibit/$id") },
                            onOrganizationSelected = { id -> navController.navigate("organization/$id") },
                        )
                    }
                    composable(
                        route = "organization/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val organizationId = backStackEntry.arguments?.getLong("id")
                        organizationId?.let { id ->
                            OrganizationDetailScreen(id, onCollectionSelected = { collectionId -> navController.navigate("collection/$collectionId") } )
                        }
                    }
                    composable(
                        route = "collection/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val organizationId = backStackEntry.arguments?.getLong("id")
                        organizationId?.let { id ->
                            CollectionDetailsScreen(id) { itemId -> navController.navigate("exhibit/$itemId") }
                        }
                    }
                    composable(
                        route = "exhibit/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val organizationId = backStackEntry.arguments?.getLong("id")
                        organizationId?.let { id ->
                            PublicationDetailScreen(id)
                        }
                    }
                }
            }
        }
    }
}