package com.sangmin.firstaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sangmin.firstaid.auth.IntroActivity
import com.sangmin.firstaid.auth.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



////     로그아웃 기능 구현
//        findViewById<Button>(R.id.logoutBtn).setOnClickListener {
//
//            auth.signOut()
//
//
//            val intent = Intent(this, IntroActivity::class.java)
////            기존에 있던 Activity들을 날려주겠다
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//
//
//        }
    }
}