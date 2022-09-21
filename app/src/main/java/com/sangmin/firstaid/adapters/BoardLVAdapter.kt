package com.sangmin.firstaid.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.sangmin.firstaid.R
import com.sangmin.firstaid.data.BoardModel
import com.sangmin.firstaid.utils.FBAuth

class BoardLVAdapter(val boardList : MutableList<BoardModel>) : BaseAdapter(){

    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView

//        if (view  == null){

            view  = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent, false)
//        }



        val itemLinearLayoutView = view?.findViewById<LinearLayout>(R.id.itemView)


        val title = view?.findViewById<TextView>(R.id.titleTxt)
        val content = view?.findViewById<TextView>(R.id.contentTxt)
        val time = view?.findViewById<TextView>(R.id.timeTxt)

        if (boardList[position].uid.equals(FBAuth.getUid())){
            itemLinearLayoutView?.setBackgroundColor(Color.parseColor("#999999"))

        }


        title!!.text = boardList[position].title
        content!!.text = boardList[position].content
        time!!.text = boardList[position].time

        return view!!
    }
}