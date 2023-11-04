package com.walletmix.pagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.walletmix.pagination.api.QuotesApi
import com.walletmix.pagination.models.ResponseQuotes
import com.walletmix.pagination.paging.QuotesPagingSource
import com.walletmix.pagination.repositories.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val repository: QuotesRepository,
    private val quotesApi: QuotesApi
) : ViewModel() {
    val quotesList = repository.getQuotes().cachedIn(viewModelScope)


    fun getAllQuotes(): Flow<PagingData<ResponseQuotes.Result>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false, maxSize = 100)
    ) {
        QuotesPagingSource(quotesApi)
    }.flow.cachedIn(viewModelScope)

}