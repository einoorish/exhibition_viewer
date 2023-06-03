package com.example.exhibitionsviewer.data.repository

import com.example.exhibitionsviewer.data.api.ApiHelper
import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.ExhibitSubjects
import com.example.exhibitionsviewer.data.model.ExhibitType
import com.example.exhibitionsviewer.data.model.FilterData
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getPublications(filterData: FilterData? = null, offset: Int): List<Publication>? {
        return if(filterData != null) {
            apiHelper.getPublications(filterData, offset)
        } else {
            apiHelper.getPublications(FilterData(), offset)
        }
    }

    suspend fun getPublication(id: Long): Publication {
        return apiHelper.getPublication(id)
    }

    suspend fun getOrganizations(searchPhrase: String, offset: Int): List<Organization> {
        val organizations = apiHelper.getOrganizations(searchPhrase, offset)
        for (organization in organizations) {
            organization.directions = organization.directions.split(",").map { it.trim() }
                .joinToString(" ") { "#" + ExhibitType.valueOf(it).ukr }
        }
        return organizations
    }

    suspend fun getOrganization(id: Long): Organization {
        val organization = apiHelper.getOrganization(id)
        organization.directions = organization.directions.split(",").map { it.trim() }
            .joinToString(" ") { "#" + ExhibitType.valueOf(it).ukr }
        return organization
    }

    suspend fun getCategoriesForOrganization(id: Long): List<Category> {
        return apiHelper.getCategoriesForOrganization(id);
    }

    suspend fun getCategory(id: Long): Category {
        return apiHelper.getCategory(id);
    }

}