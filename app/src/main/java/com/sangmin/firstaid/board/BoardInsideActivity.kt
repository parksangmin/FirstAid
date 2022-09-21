package com.sangmin.firstaid.board

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import java.lang.Exception

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding : ActivityBoardInsideBinding

    private lateinit var key : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_board_inside)

        binding.boardsetImg.setOnClickListener {
            showDialog()

        }



//         두번째 방법
        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

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

                    try {
                        val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                        Log.d(TAG, dataModel!!.title)

                        binding.titleTxt.text = dataModel!!.title
                        binding.textTxt.text = dataModel!!.content
                        binding.timeTxt.text = dataModel!!.time

                    } catch (e : Exception) {

                        Log.d(TAG, "삭제완료")


                    }





                }



               override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

                }
            }
//
            FBRef.boardRef.child(key).addValueEventListener(postListener)

    }


    private fun showDialog(){

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 수정/삭제")


        val alertDialog = mBuilder.show()
        alertDialog.findViewById<Button>(R.id.editBtn)?.setOnClickListener{
            Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show()

        }
        alertDialog.findViewById<Button>(R.id.delBtn)?.setOnClickListener {
            FBRef.boardRef.child(key).removeValue()
            Toast.makeText(this, "삭제완료", Toast.LENGTH_SHORT).show()
            finish()


        }
    }
}