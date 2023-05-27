package com.example.exhibitionsviewer.ui.home.component.organizations

import androidx.lifecycle.*
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class OrganizationsViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    fun getAllOrganizations(): Flow<List<Organization>> = flow {
        emit(remoteRepository.getOrganizations("", 0))
    }.flowOn(Dispatchers.IO)

}