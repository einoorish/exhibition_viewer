package com.example.exhibitionsviewer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.exhibitionsviewer.data.model.ViewedExhibits
import kotlinx.coroutines.flow.Flow

@Dao
interface ViewedDao {

    @Insert
    fun insert(viewed: ViewedExhibits)

    @Query("SELECT EXISTS(SELECT * FROM viewed_database WHERE exhibit_id = :id)")
    fun hasItem(id : Long) : Boolean

    @Query("SELECT * FROM viewed_database")
    fun getAll(): Flow<List<ViewedExhibits>>

}