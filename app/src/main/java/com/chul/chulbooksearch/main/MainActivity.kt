package com.chul.chulbooksearch.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.chul.chulbooksearch.AutoLoadScrollListener
import com.chul.chulbooksearch.R
import com.chul.chulbooksearch.SpaceItemDecoration
import com.chul.chulbooksearch.data.Book
import com.chul.chulbooksearch.databinding.ActivityMainBinding
import com.chul.chulbooksearch.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            vm = mViewModel
        }
        setupRecyclerView()
    }

    private fun setupViewModel() {
        mViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        mViewModel.openDetailEvent.observe(this) { event ->
            val book = event.getContentIfNotHandled()
            if(book != null) {
                openDetailActivity(book)
            }
        }
    }

    private fun setupRecyclerView() {
        mBinding.mainRecyclerView.adapter = SearchResultAdapter(mViewModel)
        mBinding.mainRecyclerView.addItemDecoration(SpaceItemDecoration(RecyclerView.VERTICAL, 10).apply {
            setExceptionViewType(SearchResultAdapter.VIEW_TYPE_HEADER)
        })
        mBinding.mainRecyclerView.addOnScrollListener(object: AutoLoadScrollListener() {
            override fun loadMore() {
                mViewModel.requestNextBookSearch()
            }
        })
    }

    private fun openDetailActivity(book: Book) {
        val bundle = Bundle().apply { putParcelable("book", book) }
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("book", bundle)
        }
        startActivity(intent)
    }

}