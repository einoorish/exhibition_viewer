package com.example.exhibitionsviewer.data.model

data class Publication (
    val id: Long = -1,
    val userId: Long = -1,
    val title: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val thumbnailUrl: String = "",
    val subject: String = "",
    val type: String = "",
    val epoch: String = "",
    val mediaType: String = "",
    val file: String = "",
    val fileUrl: String = "",
    var wasViewed: Boolean = false,
    var current: Boolean = false,
    val organization: String? = "",
)