package com.sangmin.firstaid.board

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sangmin.firstaid.R
import com.sangmin.firstaid.databinding.ActivityBoardInsideBinding

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding : ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_inside)


        val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()

        binding.titleTxt.text = title
        binding.textTxt.text = content
        binding.timeTxt.text = time


        Log.d(TAG, title)
        Log.d(TAG, content)
        Log.d(TAG, time)

    }
}