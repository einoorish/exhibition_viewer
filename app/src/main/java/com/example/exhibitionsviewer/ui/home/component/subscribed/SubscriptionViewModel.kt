package com.example.exhibitionsviewer.ui.home.component.subscribed

import androidx.lifecycle.*
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.data.model.Subscription
import com.example.exhibitionsviewer.data.model.ViewedExhibits
import com.example.exhibitionsviewer.data.repository.MainRepository
import com.example.exhibitionsviewer.data.repository.SubscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    fun getAllPublications(): Flow<List<Publication>> = flow {
        emit(mainRepository.getPublications("", 0))
    }.flowOn(Dispatchers.IO)


    private var subscriptionIds: List<Subscription>? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            subscriptionRepository.getAll().collect {
                subscriptionIds = it
            }
        }
    }

    fun subscribe(id: Long, name: String){
        if(!isSubscribed(id)) {
            viewModelScope.launch(Dispatchers.IO) {
                subscriptionRepository.add(id, name);
            }
        }
    }

    private fun isSubscribed(id: Long): Boolean {
        var result = subscriptionIds?.map { it.organization_id }?.contains(id)
        if (result == null) result = false
        return result
    }

    fun getSubscriptions() = subscriptionIds

}