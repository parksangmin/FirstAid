package com.sangmin.firstaid.utils

import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class FBAuth {

    //    Firebase Uid를 공용으로 쓸 수 있게 만듬.

    companion object {

        private lateinit var auth: FirebaseAuth

        fun getUid(): String {

            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }

        fun getTime() : String {

            val currentDateTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)

            return dateFormat
        }

    }
}