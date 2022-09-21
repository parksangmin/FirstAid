package com.sangmin.firstaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sangmin.firstaid.adapters.MainViewPagerAdapter
import com.sangmin.firstaid.auth.IntroActivity
import com.sangmin.firstaid.auth.LoginActivity
import com.sangmin.firstaid.databinding.ActivityMainBinding
import com.sangmin.firstaid.setting.SettingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var mBinding : ActivityMainBinding

    lateinit var mPagerAdapter : MainViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth


        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mPagerAdapter = MainViewPagerAdapter(this)
        mBinding.mainViewPager.adapter = mPagerAdapter


        findViewById<ImageView>(R.id.logoutImg).setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }


//        뷰페이저 연동 event
        mBinding.mainViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mBinding.bottomNav.menu.getItem(position).isChecked = true
                }
            }
        )



//        바텀 네비게이션 클릭 이벤트 처리
        mBinding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> mBinding.mainViewPager.currentItem = 0
                R.id.bookmark -> mBinding.mainViewPager.currentItem = 1
                R.id.board -> mBinding.mainViewPager.currentItem = 2

            }

            return@setOnItemSelectedListener true
        }








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