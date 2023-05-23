package com.example.exhibitionsviewer.ui.home.component.organizations

import androidx.lifecycle.*
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.data.repository.MainRepository
import com.example.exhibitionsviewer.data.repository.SubscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class OrganizationsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getAllOrganizations(): Flow<List<Organization>> = flow {
        emit(mainRepository.getOrganizations("", 0))
    }.flowOn(Dispatchers.IO)

}