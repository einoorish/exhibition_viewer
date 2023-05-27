package com.example.exhibitionsviewer.ui.home.component.organization_details

import androidx.lifecycle.*
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    private val _dataFlow = MutableStateFlow<List<Category>?>(null)
    val dataFlow: Flow<List<Category>?> = _dataFlow

    fun getOrganization(id: Long): Flow<Organization> = flow {
        emit(remoteRepository.getOrganization(id))
    }.flowOn(Dispatchers.IO)

    fun getCategoriesForOrganization(id: Long) {
        viewModelScope.launch {
            val data = remoteRepository.getCategoriesForOrganization(id)
            _dataFlow.value = data
        }
    }

}