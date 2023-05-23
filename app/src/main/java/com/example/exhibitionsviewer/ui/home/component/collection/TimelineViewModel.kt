package com.example.exhibitionsviewer.ui.home.component.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.repository.MainRepository
import com.example.exhibitionsviewer.data.repository.ViewedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val viewedRepository: ViewedRepository
) : ViewModel() {

    private val _dataFlow = MutableStateFlow<Category?>(null)
    val dataFlow: Flow<Category?> = _dataFlow

    fun getCategory(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = mainRepository.getCategory(id)
            _dataFlow.value = data
        }
    }

    fun setIdViewed(id: Long){
        if(!viewedRepository.isViewed(id)) {
            viewedRepository.addId(id)
        }
    }

    fun isIdViewed(id: Long): Boolean = viewedRepository.isViewed(id)
}