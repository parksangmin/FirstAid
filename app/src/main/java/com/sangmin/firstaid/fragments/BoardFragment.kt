package com.sangmin.firstaid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sangmin.firstaid.R
import com.sangmin.firstaid.databinding.FragmentHomeBinding

class BoardFragment : Fragment(){



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board,container,false)
    }
}