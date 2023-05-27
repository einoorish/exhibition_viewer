package com.example.exhibitionsviewer.ui.home.component.exhibits

import androidx.lifecycle.*
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.data.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class ExhibitsViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    fun getAllPublications(): Flow<List<Publication>> = flow {
        emit(remoteRepository.getPublications("", 0))
    }.flowOn(Dispatchers.IO)

}