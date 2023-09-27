package com.walletmix.pagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.walletmix.pagination.repositories.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(repository: QuotesRepository) : ViewModel() {

    val quotesList = repository.getQuotes().cachedIn(viewModelScope)

    //.cachedIn(viewModel = this.viewModelScope)
}