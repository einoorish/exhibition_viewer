package com.example.exhibitionsviewer.ui.home.component.exhibit_details

import androidx.lifecycle.*
import com.example.exhibitionsviewer.data.model.Publication
import com.example.exhibitionsviewer.data.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ExhibitDetailsViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    @field:[Inject Named("BASE_URL")]
    lateinit var BASE_URL: String

    fun getPublication(id: Long): Flow<Publication> = flow {
        emit(remoteRepository.getPublication(id))
    }.flowOn(Dispatchers.IO)

    fun getFileRequestUrl(publication: Publication?): String {
        return BASE_URL+"exhibit_media/"+publication?.id
    }

}