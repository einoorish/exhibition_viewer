package com.example.exhibitionsviewer.data.api

//import com.example.giphyapp.ui.grid.ITEMS_PER_PAGE_LIMIT
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("limit", "16")
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}