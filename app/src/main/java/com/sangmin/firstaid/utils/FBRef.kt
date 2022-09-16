package com.sangmin.firstaid.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {

    companion object{

        private val database = Firebase.database


        val category1  = database.getReference("firstaid")
        val category2  = database.getReference("firstaid2")



        val bookmarkRef = database.getReference("bookmark_list")
    }
}