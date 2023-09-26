package com.walletmix.pagination.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.walletmix.pagination.api.QuotesApi
import com.walletmix.pagination.paging.QuotesPagingSource
import javax.inject.Inject

class QuotesRepository @Inject constructor(private val quotesApi: QuotesApi) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { QuotesPagingSource(quotesApi) }
    ).liveData
}