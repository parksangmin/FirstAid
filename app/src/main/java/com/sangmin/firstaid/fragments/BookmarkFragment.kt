package com.sangmin.firstaid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sangmin.firstaid.R
import com.sangmin.firstaid.adapters.BookmarkRVAdapter
import com.sangmin.firstaid.data.Model
import com.sangmin.firstaid.databinding.FragmentBookmarkBinding
import com.sangmin.firstaid.utils.FBAuth
import com.sangmin.firstaid.utils.FBRef


class BookmarkFragment : Fragment() {

    private lateinit var binding : FragmentBookmarkBinding

    private val TAG = BookmarkFragment :: class.java.simpleName


    val items = ArrayList<Model>()

//    추가 5
    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter : BookmarkRVAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //        2. 사용자가 북마크한 정보를 다 가져옴!
        getBookmarkData()


//     추가 6
        rvAdapter = BookmarkRVAdapter(requireContext(), items,bookmarkIdList)

        val rv : RecyclerView = binding.BookmarkRV
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext())




    }

//    private fun getCategoryData(){
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//
//
//
//
//
//
//
//                for (dataModel in dataSnapshot.children) {
//
//                    Log.d(TAG, dataModel.toString())
//                    val item = dataModel.getValue(Model::class.java)
//
//                    //     3. 전체 켄테츠 중에서, 사용자가 북마크한 정보만 보여줌!
//                    if(bookmarkIdList.contains(dataModel.key.toString())){
//                        items.add(item!!)
//                        itemKeyList.add(dataModel.key.toString())
//
//
//
//
//                    }
//
//
//
//                }
//                rvAdapter.notifyDataSetChanged()
////                어댑터를 동기화하는 작업
//
//
//
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w("CategoryListActivity", "loadPost:onCancelled", databaseError.toException())
//            }
//        }
//        FBRef.category1.addValueEventListener(postListener)
//        FBRef.category2.addValueEventListener(postListener)
//        FBRef.category3.addValueEventListener(postListener)
//        FBRef.category4.addValueEventListener(postListener)





//    }

    private fun getBookmarkData(){

        //        Firebase Realtime Database 데이터 읽기
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                items.clear()
                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(Model::class.java)
                    if (item != null) {
                        items.add(item)
                    }
                }
                rvAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Category1ListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)


    }





}