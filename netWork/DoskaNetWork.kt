package com.example.doskamura.netWork

import com.example.doskamura.netWork.api.DoskApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DoskaNetWork private constructor() {
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val doskaApi: DoskApi
        get() = retrofit.create(DoskApi::class.java)

    companion object {
        var instance: DoskaNetWork? = null
            get() {
                if (field == null) {
                    field = DoskaNetWork()
                }
                return field
            }
            private set
        private const val BASE_URL = "https://doska.ykt.ru"
    }
}
