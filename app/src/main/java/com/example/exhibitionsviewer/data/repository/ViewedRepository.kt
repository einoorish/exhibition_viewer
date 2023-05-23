package com.example.exhibitionsviewer.data.repository

import com.example.exhibitionsviewer.data.dao.ViewedDao
import com.example.exhibitionsviewer.data.dao.MainDatabase
import com.example.exhibitionsviewer.data.model.ViewedExhibits
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ViewedRepository @Inject constructor(db: MainDatabase)  {

    private var viewedDao: ViewedDao

    init {
        viewedDao = db.viewedDao()
    }

    fun addId(id: Long){
        viewedDao.insert(ViewedExhibits(id))
    }

    fun isViewed(id: Long): Boolean {
        return viewedDao.hasItem(id);
    }
}