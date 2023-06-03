package com.example.exhibitionsviewer.ui.home.component.subscribed

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.*
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.data.model.Subscription
import com.example.exhibitionsviewer.data.repository.RemoteRepository
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
    private val remoteRepository: RemoteRepository,
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {

    init {
        getAllPublications()
    }

    fun getAllPublications(): Flow<List<Publication>?> = flow {
        emit(remoteRepository.getPublications(offset =  0))
    }.flowOn(Dispatchers.IO)


    private var subscriptionIds = mutableStateListOf<Subscription>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            subscriptionRepository.getAll().collect { elements ->
                subscriptionIds.addAll(elements)
            }
        }
    }

    fun subscribe(id: Long, name: String){
        viewModelScope.launch(Dispatchers.IO) {
            subscriptionRepository.add(id, name)
            subscriptionIds.add(Subscription(id, name))
        }
    }

    fun unsubscribe(id: Long, organizationName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            subscriptionRepository.remove(id, organizationName)
            subscriptionIds.remove(Subscription(id, organizationName))
        }
    }

    fun isSubscribed(id: Long): Boolean {
        return subscriptionIds.map { it.organization_id }.contains(id)
    }

    fun getSubscriptions() = subscriptionIds

}