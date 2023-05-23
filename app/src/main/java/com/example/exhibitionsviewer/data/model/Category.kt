package com.example.exhibitionsviewer.data.model

data class Category(
    val id: Long,
    val title: String,
    val description: String,
    val thumbnail: String,
    val thumbnailUrl: String,
    val userId: Long,
    val objects: List<Publication>
)