package com.sangmin.firstaid.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.sangmin.firstaid.R
import com.sangmin.firstaid.data.BoardModel
import com.sangmin.firstaid.databinding.ActivityBoardBinding
import com.sangmin.firstaid.utils.FBAuth
import com.sangmin.firstaid.utils.FBRef

class BoardActivity : AppCompatActivity() {

    lateinit var binding:ActivityBoardBinding

    private val TAG = BoardActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board)

        binding.writeBtn.setOnClickListener {

            val title = binding.titleEdt.text.toString()
            val content = binding.contentEdt.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()


            Log.d(TAG, title)
            Log.d(TAG, content)

//            board
//            key
//            boardModel(title, content, uid, time)


            FBRef.boardRef
                .push()
                .setValue(BoardModel(title, content, uid, time))


            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_SHORT).show()
//
            finish()

        }
    }
}