package com.example.exhibitionsviewer.data.api

import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getPublications(searchPhrase: String, offset: Int): List<Publication> = apiService.getPublications(searchPhrase, offset)

    suspend fun getPublication(id: Long): Publication = apiService.getPublication(id)

    suspend fun getFilteredPublications(searchPhrase: String, offset: Int, subject: String?, type: String?, mediaType: String?): List<Publication> {
        return apiService.getFilteredPublications(
            searchPhrase = searchPhrase,
            offset = offset,
            subject = subject,
            type = type,
            mediaType = mediaType
        )
    }

    suspend fun getOrganizations(searchPhrase: String, offset: Int): List<Organization> = apiService.getOrganizations(searchPhrase, offset)

    suspend fun getOrganization(id: Long): Organization = apiService.getOrganization(id)

    suspend fun getCategoriesForOrganization(id: Long): List<Category> = apiService.getOrganizationCategories(id)

    suspend fun getCategory(id: Long): Category = apiService.getCategory(id)

}
