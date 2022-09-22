package com.sangmin.firstaid.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.sangmin.firstaid.R
import com.sangmin.firstaid.data.CommentModel
import com.sangmin.firstaid.utils.FBAuth

class CommentLVAdapter(val commentList : MutableList<CommentModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return commentList.size

    }

    override fun getItem(position: Int): Any {
       return commentList[position]

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {


        var view = converView


        if (view == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.comment_list_item, parent, false)
        }




        val title = view?.findViewById<TextView>(R.id.titleTxt)
        val time = view?.findViewById<TextView>(R.id.timeTxt)




        title!!.text = commentList[position].commentTitle
        time!!.text = commentList[position].commentTime

        return view!!


    }
}