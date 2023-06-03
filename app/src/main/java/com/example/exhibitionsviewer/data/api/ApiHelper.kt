package com.example.exhibitionsviewer.data.api

import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.FilterData
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getPublications(filterData: FilterData, offset: Int): List<Publication>? =
        apiService.getPublications(
            searchPhrase = filterData.text,
            type = filterData.type.replace(" ", "_"),
            subject = filterData.subject.replace(" ", "_"),
            epoch = filterData.epoch.replace(" ", "_"),
            offset
        )

    suspend fun getPublication(id: Long): Publication = apiService.getPublication(id)

    suspend fun getOrganizations(searchPhrase: String, offset: Int): List<Organization> = apiService.getOrganizations(searchPhrase, offset)

    suspend fun getOrganization(id: Long): Organization = apiService.getOrganization(id)

    suspend fun getCategoriesForOrganization(id: Long): List<Category> = apiService.getOrganizationCategories(id)

    suspend fun getCategory(id: Long): Category = apiService.getCategory(id)

}
