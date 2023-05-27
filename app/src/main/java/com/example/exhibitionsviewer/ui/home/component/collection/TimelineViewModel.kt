package com.example.exhibitionsviewer.ui.home.component.collection

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.ViewedExhibits
import com.example.exhibitionsviewer.data.repository.RemoteRepository
import com.example.exhibitionsviewer.data.repository.ViewedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val viewedRepository: ViewedRepository
) : ViewModel() {

    private val _dataFlow = MutableStateFlow<Category?>(null)
    val dataFlow: Flow<Category?> = _dataFlow

    private val viewedIds = mutableStateListOf<Long>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            viewedRepository.getAll().collect { viewedExhibitsList ->
                viewedIds.addAll(viewedExhibitsList.map { it.exhibit_id })
            }
        }
    }

    fun getCategory(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = remoteRepository.getCategory(id)
            _dataFlow.value = data
        }
    }

    fun setIdViewed(id: Long){
        if(!isIdViewed(id)) {
            viewModelScope.launch(Dispatchers.IO) {
                viewedRepository.addId(id)
            }
        }
    }

    fun isIdViewed(id: Long): Boolean {
        return viewedIds.any { it == id }
    }
}