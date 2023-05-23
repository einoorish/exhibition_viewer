package com.example.exhibitionsviewer.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "subscription")
data class Subscription (
    @PrimaryKey
    val organization_id: Long,
    val name: String
)
