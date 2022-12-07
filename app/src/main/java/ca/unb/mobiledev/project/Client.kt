package ca.unb.mobiledev.project

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {

    private var api: API;

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI = retrofit.create(API::class.java)
        api = retrofitAPI
    }

    fun getAPI(): API {
        return api;
    }

}