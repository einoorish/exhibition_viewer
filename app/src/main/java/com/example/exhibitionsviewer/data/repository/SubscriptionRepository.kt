package com.example.exhibitionsviewer.data.repository

import com.example.exhibitionsviewer.data.dao.ViewedDao
import com.example.exhibitionsviewer.data.dao.MainDatabase
import com.example.exhibitionsviewer.data.dao.SubscriptionDao
import com.example.exhibitionsviewer.data.model.Subscription
import com.example.exhibitionsviewer.data.model.ViewedExhibits
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SubscriptionRepository @Inject constructor(db: MainDatabase)  {

    private var subscriptionDao: SubscriptionDao

    init {
        subscriptionDao = db.subscribedDao()
    }

    fun add(id: Long, name: String){
        subscriptionDao.insert(Subscription(id, name))
    }

    fun getAll(): Flow<List<Subscription>> {
        return subscriptionDao.getAll()
    }

}