package com.chul.chulbooksearch.main

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.chul.chulbooksearch.R

abstract class HeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val editText: EditText = itemView.findViewById(R.id.item_header_edit_text)
    private val handler = object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                search -> {
                    onSearch(editText.text.toString())
                }
            }
        }
    }
    private val search = 0

    init {
        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                handler.removeMessages(search)
                if(!text?.trim().isNullOrEmpty()) {
                    handler.sendEmptyMessageDelayed(search, 1000)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    abstract fun onSearch(text: String)
}