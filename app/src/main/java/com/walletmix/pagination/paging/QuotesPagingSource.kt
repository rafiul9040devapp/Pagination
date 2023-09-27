package com.walletmix.pagination.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.walletmix.pagination.api.QuotesApi
import com.walletmix.pagination.models.ResponseQuotes

class QuotesPagingSource(private val quotesApi: QuotesApi) :
    PagingSource<Int, ResponseQuotes.Result>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, ResponseQuotes.Result> {

        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = quotesApi.getAllQuotes(position)

            LoadResult.Page(
                data = response.results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResponseQuotes.Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}