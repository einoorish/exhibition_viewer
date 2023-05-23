package com.example.exhibitionsviewer.ui.home.component.exhibit_details

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
class ExhibitDetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    @field:[Inject Named("BASE_URL")]
    lateinit var BASE_URL: String

    fun getPublication(id: Long): Flow<Publication> = flow {
        emit(mainRepository.getPublication(id))
    }.flowOn(Dispatchers.IO)

    fun getFileRequestUrl(publication: Publication?): String {
        return BASE_URL+"exhibit_media/"+publication?.id
    }

}