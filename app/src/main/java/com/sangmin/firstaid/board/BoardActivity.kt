package com.sangmin.firstaid.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sangmin.firstaid.R
import com.sangmin.firstaid.databinding.ActivityBoardBinding

class BoardActivity : AppCompatActivity() {

    lateinit var binding:ActivityBoardBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board)
    }
}