package com.example.exhibitionsviewer.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "viewed_database")
data class ViewedExhibits (
    @PrimaryKey
    val exhibit_id: Long,
)
