package com.example.makeu.api

import com.example.makeu.model.MakeupItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {

    @GET("/api/v1/products.json")
      fun getItems(@Query("brand") brand:String) : Call <ArrayList<MakeupItem>>
}