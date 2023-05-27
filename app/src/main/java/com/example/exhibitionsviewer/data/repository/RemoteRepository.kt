package com.example.exhibitionsviewer.data.repository

import com.example.exhibitionsviewer.data.api.ApiHelper
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPublications(searchPhrase: String, offset: Int): List<Publication> {
        return apiHelper.getPublications(searchPhrase, offset)
    }

    suspend fun getPublication(id: Long): Publication {
        return apiHelper.getPublication(id)
    }

    suspend fun getOrganizations(searchPhrase: String, offset: Int): List<Organization> {
        val organizations = apiHelper.getOrganizations(searchPhrase, offset)
        for (organization in organizations) {
            organization.directions = "#"+organization.directions.replace(",", " #")
        }
        return organizations
    }

    suspend fun getOrganization(id: Long): Organization {
        val organization = apiHelper.getOrganization(id)
        organization.directions = "#"+organization.directions.replace(",", " #")
        return organization
    }

    suspend fun getCategoriesForOrganization(id: Long): List<Category> {
        return apiHelper.getCategoriesForOrganization(id);
    }

    suspend fun getCategory(id: Long): Category {
        return apiHelper.getCategory(id);
    }

}