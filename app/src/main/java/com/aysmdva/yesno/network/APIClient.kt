package com.aysmdva.yesno.network

import com.aysmdva.yesno.api.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    val api: API

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://yesno.wtf/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(API::class.java)
    }

    companion object {
        var client: APIClient? = null
            get() {
                if (field == null) {
                    field = APIClient()
                }
                return field
            }
            private set

    }

    fun getAPI() :API{
        return api
    }
}