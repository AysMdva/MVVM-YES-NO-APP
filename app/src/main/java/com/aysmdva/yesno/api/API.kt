package com.aysmdva.yesno.api

import com.aysmdva.yesno.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("api")
    fun setAnim() :Call<Data>
}