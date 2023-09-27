package com.walletmix.pagination.view

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.walletmix.pagination.R
import com.walletmix.pagination.databinding.ActivityMainBinding
import com.walletmix.pagination.paging.LoaderAdapter
import com.walletmix.pagination.paging.QuotesAdapter
import com.walletmix.pagination.viewmodel.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: QuotesAdapter
    private val viewModel: QuotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window,window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        adapter = QuotesAdapter()
        binding.rcvQuotes.setHasFixedSize(true)
        binding.rcvQuotes.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        viewModel.quotesList.observe(this) {
            adapter.submitData(lifecycle = lifecycle,it)
        }
    }
}