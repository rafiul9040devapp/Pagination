package com.walletmix.pagination.api

import com.walletmix.pagination.models.ResponseQuotes
import com.walletmix.pagination.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {

    @GET(Constants.QUOTES_END_POINT)
    suspend fun getAllQuotes(@Query("page") page: Int): ResponseQuotes
}