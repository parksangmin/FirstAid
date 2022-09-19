package com.sangmin.firstaid.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sangmin.firstaid.R
import com.sangmin.firstaid.board.BoardActivity
import com.sangmin.firstaid.databinding.FragmentBoardBinding

class BoardFragment : Fragment(){

    lateinit var binding : FragmentBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardActivity::class.java)
            startActivity(intent)
        }
    }
}