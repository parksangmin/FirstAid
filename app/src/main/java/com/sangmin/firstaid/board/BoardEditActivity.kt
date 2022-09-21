package com.sangmin.firstaid.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.sangmin.firstaid.databinding.ActivityBoardEditBinding
import com.sangmin.firstaid.utils.FBRef
import java.lang.Exception

class BoardEditActivity : AppCompatActivity() {

    private lateinit var key : String

    private lateinit var binding: ActivityBoardEditBinding

    private val TAG = BoardEditActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_edit)

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)
    }


    private fun getBoardData(key : String){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
//                Log.d(TAG, dataModel.toString())
//                Log.d(TAG, dataModel!!.title)
//                Log.d(TAG, dataModel.time)

                binding.titleEdt.setText(dataModel?.title)
                binding.contentEdt.setText(dataModel?.content)

            }



            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

            }
        }
//
        FBRef.boardRef.child(key).addValueEventListener(postListener)

    }

    private fun getImageData(key: String){
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageView = binding.boardImg


        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(imageView)
            } else {

            }
        })





    }





}