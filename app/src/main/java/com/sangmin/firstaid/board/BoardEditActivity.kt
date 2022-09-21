package com.sangmin.firstaid.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.sangmin.firstaid.utils.FBAuth
import com.sangmin.firstaid.utils.FBRef
import java.lang.Exception

class BoardEditActivity : AppCompatActivity() {

    private lateinit var key : String

    private lateinit var binding: ActivityBoardEditBinding

    private val TAG = BoardEditActivity::class.java.simpleName

    private lateinit var writerUid : String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_edit)

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)
        binding.editBtn.setOnClickListener {
            editBoardData(key)
        }

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
                writerUid = dataModel!!.uid


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


    private fun editBoardData(key : String){

//        writerUid 말고 FBAuth.getuid로 해보기
        FBRef.boardRef
            .child(key)
            .setValue(
                BoardModel(binding.titleEdt.text.toString(),
                    binding.contentEdt.text.toString(),
                    writerUid,
                    FBAuth.getTime()))

        Toast.makeText(this, "수정완료", Toast.LENGTH_SHORT).show()

        finish()

    }






}