package com.chul.chulbooksearch.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chul.chulbooksearch.R
import com.chul.chulbooksearch.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity() {

    private lateinit var mBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail).apply {
            lifecycleOwner = this@DetailActivity
            book = intent.getBundleExtra("book")?.getParcelable("book")
        }
    }
}