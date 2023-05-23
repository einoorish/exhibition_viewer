package com.example.exhibitionsviewer.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exhibitionsviewer.data.model.Subscription
import com.example.exhibitionsviewer.data.model.ViewedExhibits

@Database(entities = [ViewedExhibits::class, Subscription::class], version = 3)
abstract class MainDatabase: RoomDatabase() {

    abstract fun viewedDao(): ViewedDao

    abstract fun subscribedDao(): SubscriptionDao

}