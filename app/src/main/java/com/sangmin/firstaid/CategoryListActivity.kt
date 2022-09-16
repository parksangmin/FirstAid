package com.sangmin.firstaid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sangmin.firstaid.adapters.CategoryRVAdapter
import com.sangmin.firstaid.data.Model
import com.sangmin.firstaid.utils.FBAuth
import com.sangmin.firstaid.utils.FBRef


class CategoryListActivity : AppCompatActivity() {

   lateinit var myRef : DatabaseReference

    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter : CategoryRVAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category1_list)

        //     타이틀과 Imgurl, Weburl 추가
        val items = ArrayList<Model>()
        // 아이템 키 리스트 추가
        val itemKeyList = ArrayList<String>()


         rvAdapter = CategoryRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

        // Write a message to the database
        val database = Firebase.database





        val category = intent.getStringExtra("category")

        if (category == "category1") {

            myRef = database.getReference("firstaid")


        } else if (category == "category2") {

            myRef = database.getReference("firstaid2")


        } else if (category == "category3") {

            myRef = database.getReference("firstaid3")

        } else if (category == "category4") {

            myRef = database.getReference("firstaid4")
        }

        //        Firebase Realtime Database 데이터 읽기
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    Log.d("CategoryListActivity", dataModel.toString())
                    // 키를 확인하기 위한 로그
                    Log.d("CategoryListActivity", dataModel.key.toString())
                    val item = dataModel.getValue(Model::class.java)
                    items.add(item!!)
//                    파이어 베이스에 있는 데이터 키값을 확인
                    itemKeyList.add(dataModel.key.toString())

                }
//                어댑터를 동기화하는 작업
                rvAdapter.notifyDataSetChanged()
                Log.d("CategoryListActivity", items.toString())


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("CategoryListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)



        val rv: RecyclerView = findViewById(R.id.rv)






        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(this)

        getBookmarkData()





    }

    private fun getBookmarkData() {

        //        Firebase Realtime Database 데이터 읽기
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {



                for(dataModel in dataSnapshot.children){
                    bookmarkIdList.add(dataModel.key.toString())


                }
                Log.d("CategoryListActivity", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("CategoryListActivity", "loadPost:onCancelled", databaseError.toException())

            }
        }
//
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)


    }

}