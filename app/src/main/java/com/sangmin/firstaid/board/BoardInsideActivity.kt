package com.sangmin.firstaid.board

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sangmin.firstaid.R
import com.sangmin.firstaid.data.BoardModel
import com.sangmin.firstaid.databinding.ActivityBoardInsideBinding
import com.sangmin.firstaid.utils.FBRef

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding : ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_inside)

//  첫번쨰 방법
//        val title = intent.getStringExtra("title").toString()
//        val content = intent.getStringExtra("content").toString()
//        val time = intent.getStringExtra("time").toString()
//
//        binding.titleTxt.text = title
//        binding.textTxt.text = content
//        binding.timeTxt.text = time


//        Log.d(TAG, title)
//        Log.d(TAG, content)
//        Log.d(TAG, time)

//         두번째 방법
        val key = intent.getStringExtra("key")
        getBoardData(key.toString())
        getImageData(key.toString())

    }

    private fun getImageData(key: String){
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageView = binding.getImg


        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(imageView)
            } else {

            }
        })





    }

    private fun getBoardData(key : String) {

            //        Firebase Realtime Database 데이터 읽기
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    Log.d(TAG, dataModel!!.title)

                    binding.titleTxt.text = dataModel!!.title
                    binding.textTxt.text = dataModel!!.content
                    binding.timeTxt.text = dataModel!!.time



                }



                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

                }
            }
//
            FBRef.boardRef.child(key).addValueEventListener(postListener)

    }
}