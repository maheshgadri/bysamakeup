package com.example.makeu.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.makeu.api.ItemService
import com.example.makeu.db.ItemDao
import com.example.makeu.model.MakeupItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemService: ItemService,
                                         private val itemDao: ItemDao) {


    fun getItem(): LiveData<List<MakeupItem>>{
        return itemDao.getItems()
    }

    fun insertItem(makeupItem: MakeupItem) {
//        var makupData: ArrayList<MakeupItem> = ArrayList()
        itemDao.additems(makeupItem)
    }

    fun makeApiCall(brand:String){
        val call: Call<ArrayList<MakeupItem>> = itemService.getItems("maybelline")
call.enqueue(object :Callback<ArrayList<MakeupItem>>{
    override fun onResponse(call: Call<ArrayList<MakeupItem>>, response: Response<ArrayList<MakeupItem>>) {

        if(response.isSuccessful  && response.body() != null) {
           Log.d("<<<",response.body().toString())
            itemDao.deleteAllRecords()
            response.body()!!.forEach {
                insertItem(it)
            }
//            response.body()?.let { itemDao.additems(it) }


            }
        }

    override fun onFailure(call: Call<ArrayList<MakeupItem>>, t: Throwable) {
//       Toast.makeText(getApplicationContext(),"Failure in Connection",Toast.LENGTH_LONG).show()
    }
}



)

    }
}