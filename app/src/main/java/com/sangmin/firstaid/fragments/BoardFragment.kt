package com.sangmin.firstaid.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sangmin.firstaid.R
import com.sangmin.firstaid.adapters.BoardLVAdapter
import com.sangmin.firstaid.board.BoardActivity
import com.sangmin.firstaid.board.BoardInsideActivity
import com.sangmin.firstaid.data.BoardModel
import com.sangmin.firstaid.databinding.FragmentBoardBinding
import com.sangmin.firstaid.utils.FBAuth
import com.sangmin.firstaid.utils.FBRef

class BoardFragment : Fragment(){

    lateinit var binding : FragmentBoardBinding

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private val TAG = BoardFragment ::class.java.simpleName

    private lateinit var BoardAdapter : BoardLVAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardActivity::class.java)
            startActivity(intent)
        }

//        val boardList = mutableListOf<BoardModel>()
//        boardList.add(BoardModel("a","B","c", "d"))

        BoardAdapter = BoardLVAdapter(boardDataList)
        binding.boardListView.adapter = BoardAdapter


//        첫번쨰 방법으로는 listView에 있는 데이터 title content time 다 다른 액티비티로 전달해줘서 만들기
        binding.boardListView.setOnItemClickListener { parent, view, position, id ->

//            val intent = Intent(context, BoardInsideActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)

        //         두번째 방법으로는 Firebase에 있는 board에 대한 데이터의 id를 기반으로 다시 데이터를 받아오는 방법
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)


        }




        getFBBoardData()




    }


    private  fun getFBBoardData(){

        //        Firebase Realtime Database 데이터 읽기
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()



                for(dataModel in dataSnapshot.children){

                    Log.d(TAG, dataModel.toString())
//                    dataModel.key

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())

                }
                boardKeyList.reverse()
                boardDataList.reverse()
                BoardAdapter.notifyDataSetChanged()

                Log.d(TAG, boardDataList.toString())

            }



            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

            }
        }
//
        FBRef.boardRef.addValueEventListener(postListener)

    }
}