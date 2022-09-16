package com.sangmin.firstaid.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangmin.firstaid.CategoryShowActivity
import com.sangmin.firstaid.R
import com.sangmin.firstaid.data.Model


class CategoryRVAdapter(val context : Context,
                        val items : ArrayList<Model>

) : RecyclerView.Adapter<CategoryRVAdapter.Viewholder>(){


    interface ItemClick {
        fun onClick(view : View, position: Int)


    }
    var itemClick : ItemClick? = null




//   아이템들 하나를 가져온다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category1_item,parent, false)
        return Viewholder(v)
    }


//  아이템의 내용물들을 넣어주는 역할
    override fun onBindViewHolder(holder: Viewholder, position: Int) {

    if (itemClick != null){
        holder.itemView.setOnClickListener { v ->
            itemClick?.onClick(v, position)
        }
    }

        holder.bindItems(items[position])

    }


//    아이템의 갯수
    override fun getItemCount(): Int {
        return items.size

    }

    inner class  Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : Model) {




//            itemView = category1_item, imageurl1,2,3등의 title을 보여준다
            val Maintitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageView  = itemView.findViewById<ImageView>(R.id.ImgView)







            Maintitle.text = item.title

//            Glide를 이용해서 이미지 url을 가져온다
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageView)


        }
    }


}