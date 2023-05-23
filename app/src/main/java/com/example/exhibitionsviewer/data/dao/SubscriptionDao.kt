package com.example.exhibitionsviewer.data.dao

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.exhibitionsviewer.data.model.Subscription
import com.example.exhibitionsviewer.data.model.ViewedExhibits
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionDao {

    @Insert
    fun insert(subscription: Subscription)

    @Query("SELECT * FROM subscription")
    fun getAll(): Flow<List<Subscription>>

}