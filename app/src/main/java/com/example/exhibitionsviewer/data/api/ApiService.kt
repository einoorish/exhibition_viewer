package com.example.exhibitionsviewer.data.api

import com.example.exhibitionsviewer.data.model.Category
import com.example.exhibitionsviewer.data.model.Organization
import com.example.exhibitionsviewer.data.model.Publication
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(value = "/get_publication/{id}")
    suspend fun getPublication(
        @Path("id") id: Long
    ): Publication

    @GET(value = "/get_all_publications")
    suspend fun getPublications(
        @Query("q") searchPhrase: String,
        @Query("offset") offset: Int
    ): List<Publication>

    @GET(value = "/get_publications")
    suspend fun getFilteredPublications(
        @Query("text") searchPhrase: String?,
        @Query("offset") offset: Int,
        @Query("subject") subject: String?,
        @Query("type") type: String?,
        @Query("media_type") mediaType: String?,
    ): List<Publication>

    @GET(value = "/get_organization/{id}")
    suspend fun getOrganization(
        @Path("id") id: Long
    ): Organization

    @GET(value = "/get_organizations")
    suspend fun getOrganizations(
        @Query("q") searchPhrase: String,
        @Query("offset") offset: Int
    ): List<Organization>

    @GET(value = "/get_organization_publications/{id}")
    suspend fun getOrganizationPublications(
        @Path("id") id: Int
    ): List<Publication>

    @GET(value = "/get_organization_categories/{id}")
    suspend fun getOrganizationCategories(
        @Path("id") id: Long
    ): List<Category>

    @GET(value = "/get_category/{id}")
    suspend fun getCategory(@Path("id") id: Long): Category

}