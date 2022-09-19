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
import com.sangmin.firstaid.data.BookmarkModel
import com.sangmin.firstaid.data.Model
import com.sangmin.firstaid.utils.FBAuth
import com.sangmin.firstaid.utils.FBRef

class BookmarkRVAdapter(val context : Context,
                        val items : ArrayList<Model>

) : RecyclerView.Adapter<BookmarkRVAdapter.Viewholder>(){



    //   아이템들 하나를 가져온다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category1_item,parent, false)

        return Viewholder(v)
    }


    //  아이템의 내용물들을 넣어주는 역할
    override fun onBindViewHolder(holder: Viewholder, position: Int) {


        holder.bindItems(items[position])

    }


    //    아이템의 갯수
    override fun getItemCount(): Int {
        return items.size

    }

    inner class  Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : Model) {

            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                val intent = Intent(context, CategoryShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)

            }


//            itemView = category_item, imageurl1,2,3등의 title을 보여준다
            val Maintitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageView  = itemView.findViewById<ImageView>(R.id.ImgView)
            val bookmark = itemView.findViewById<ImageView>(R.id.bookmarkImg)

            bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)


            Maintitle.text = item.title

//            Glide를 이용해서 이미지 url을 가져온다
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageView)

//            북마크 해지 이벤트 처리
            bookmark.setOnClickListener {
                bookmark.setImageResource(R.drawable.bookmark_white)
            }


        }
    }


}