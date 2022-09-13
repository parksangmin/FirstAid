package com.sangmin.firstaid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sangmin.firstaid.adapters.CategoryRVAdapter
import com.sangmin.firstaid.data.Model
import com.sangmin.firstaid.utils.FBAuth
import com.sangmin.firstaid.utils.FBRef

class CategoryListActivity : AppCompatActivity() {

    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter : CategoryRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category1_list)

        //     타이틀과 Imgurl, Weburl 추가
        val items = ArrayList<Model>()
        val itemKeyList = ArrayList<String>()

        rvAdapter = CategoryRVAdapter(baseContext,items, itemKeyList, bookmarkIdList)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("firstaids")

//        Firebase Realtime Database 데이터 읽기
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children){
                    Log.d("Category1ListActivity", dataModel.toString())
                    Log.d("Category1ListActivity", dataModel.key.toString())
                    val item = dataModel.getValue(Model::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
//                어댑터를 동기화하는 작업
                rvAdapter.notifyDataSetChanged()
                Log.d("Category1ListActivity", items.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Category1ListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

//        myRef.push()
//            .setValue(Model("기도폐쇄", "https://www.gyeongnam.go.kr/01_potal/images/welfare/pic2f.gif", "https://blog.naver.com/dhwkffks7/222869936745"))
//        myRef.push()
//            .setValue(Model("중독", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/GHS-pictogram-skull.svg/250px-GHS-pictogram-skull.svg.png","https://blog.naver.com/dhwkffks7/222869940230"))
//        myRef.push()
//            .setValue(Model("심장마비", "https://www.gyeongnam.go.kr/01_potal/images/welfare/picd3.gif","https://blog.naver.com/dhwkffks7/222873265243"))
//        myRef.push()
//            .setValue(Model("무의식", "https://file.mk.co.kr/meet/neds/2020/01/image_readtop_2020_77991_15797538804062809.jpg","https://blog.naver.com/dhwkffks7/222873265909"))
//        myRef.push()
//            .setValue(Model("출혈", "https://cdn-icons-png.flaticon.com/512/2688/2688446.png","https://blog.naver.com/dhwkffks7/222873266862"))
//        myRef.push()
//            .setValue(Model("발작", "http://www.amc.seoul.kr/healthinfo/health/attach/img/30345/20111226131632_1_30345.jpg","https://blog.naver.com/dhwkffks7/222873267907"))
//        myRef.push()
//            .setValue(Model("익사사고", "https://www.gyeongnam.go.kr/01_potal/images/welfare/picfa.gif","https://blog.naver.com/dhwkffks7/222873268414"))
//        myRef.push()
//            .setValue(Model("화상", "https://mblogthumb-phinf.pstatic.net/MjAxNjEwMjhfOTYg/MDAxNDc3NjQ1MjQzMjM1.-NfYbfmvv3x7MxSOIgbuS-hnTcqFPwW66KoqYe680tEg.XUfBKIjy_9O43iIfKPNhU5lmekySm9kbdkLbaEfS-EMg.PNG.ok_hira/06.png?type=w2","https://blog.naver.com/dhwkffks7/222873269124"))
//        myRef.push()
//            .setValue(Model("일사병", "https://t1.daumcdn.net/cfile/tistory/995140395D3F95892D","https://blog.naver.com/dhwkffks7/222873269503"))
//        myRef.push()
//            .setValue(Model("전기손상", "https://mblogthumb-phinf.pstatic.net/20150325_185/radianaed_1427260553508VDRmb_JPEG/%B0%A8%C0%FC2.jpg?type=w2","https://blog.naver.com/dhwkffks7/222873270032"))
//        myRef.push()
//            .setValue(Model("자살기도", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtGSbJ2xwXiiJ3m2yo899x9R0o-S7_u3glRA&usqp=CAU","https://blog.naver.com/dhwkffks7/222873270653"))
//        myRef.push()
//            .setValue(Model("분만", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfSpaNZh1ptIgmxgygjpo_1hUmS8BtZtFwiQ&usqp=CAU","https://blog.naver.com/dhwkffks7/222873272195"))
        val rv : RecyclerView = findViewById(R.id.rv)




//        items.add(Model("기도폐쇄", "https://www.gyeongnam.go.kr/01_potal/images/welfare/pic2f.gif", "https://blog.naver.com/dhwkffks7/222869936745"))
//        items.add(Model("중독", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/GHS-pictogram-skull.svg/250px-GHS-pictogram-skull.svg.png","https://blog.naver.com/dhwkffks7/222869940230"))
//        items.add(Model("심장마비", "https://www.gyeongnam.go.kr/01_potal/images/welfare/picd3.gif","https://blog.naver.com/dhwkffks7/222873265243"))
//        items.add(Model("무의식", "https://file.mk.co.kr/meet/neds/2020/01/image_readtop_2020_77991_15797538804062809.jpg","https://blog.naver.com/dhwkffks7/222873265909"))
//        items.add(Model("출혈", "https://cdn-icons-png.flaticon.com/512/2688/2688446.png","https://blog.naver.com/dhwkffks7/222873266862"))
//        items.add(Model("척추손상", "https://www.gyeongnam.go.kr/01_potal/images/welfare/picf3.gif","https://blog.naver.com/dhwkffks7/222873267446"))
//        items.add(Model("발작", "http://www.amc.seoul.kr/healthinfo/health/attach/img/30345/20111226131632_1_30345.jpg","https://blog.naver.com/dhwkffks7/222873267907"))
//        items.add(Model("익사사고", "https://www.gyeongnam.go.kr/01_potal/images/welfare/picfa.gif","https://blog.naver.com/dhwkffks7/222873268414"))
//        items.add(Model("화상", "https://mblogthumb-phinf.pstatic.net/MjAxNjEwMjhfOTYg/MDAxNDc3NjQ1MjQzMjM1.-NfYbfmvv3x7MxSOIgbuS-hnTcqFPwW66KoqYe680tEg.XUfBKIjy_9O43iIfKPNhU5lmekySm9kbdkLbaEfS-EMg.PNG.ok_hira/06.png?type=w2","https://blog.naver.com/dhwkffks7/222873269124"))
//        items.add(Model("일사병", "https://t1.daumcdn.net/cfile/tistory/995140395D3F95892D","https://blog.naver.com/dhwkffks7/222873269503"))
//        items.add(Model("전기손상", "https://mblogthumb-phinf.pstatic.net/20150325_185/radianaed_1427260553508VDRmb_JPEG/%B0%A8%C0%FC2.jpg?type=w2","https://blog.naver.com/dhwkffks7/222873270032"))
//        items.add(Model("자살기도", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtGSbJ2xwXiiJ3m2yo899x9R0o-S7_u3glRA&usqp=CAU","https://blog.naver.com/dhwkffks7/222873270653"))
//        items.add(Model("분만", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfSpaNZh1ptIgmxgygjpo_1hUmS8BtZtFwiQ&usqp=CAU","https://blog.naver.com/dhwkffks7/222873272195"))




        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(this)

        getBookmarkData()



    }

    private fun getBookmarkData(){

        //        Firebase Realtime Database 데이터 읽기
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookmarkIdList.clear()

                for(dataModel in dataSnapshot.children){
                    bookmarkIdList.add(dataModel.key.toString())

                }
                Log.d("Bookmark :", bookmarkIdList.toString())
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