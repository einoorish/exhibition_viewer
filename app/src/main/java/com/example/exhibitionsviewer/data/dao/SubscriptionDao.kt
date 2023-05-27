package com.example.exhibitionsviewer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.exhibitionsviewer.data.model.Subscription
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionDao {

    @Insert
    fun insert(subscription: Subscription)

    @Query("SELECT * FROM subscription")
    fun getAll(): Flow<List<Subscription>>

}