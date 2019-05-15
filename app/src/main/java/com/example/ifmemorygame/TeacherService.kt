package com.example.ifmemorygame

import android.provider.ContactsContract
import com.example.ifmemorygame.model.Teacher
import com.example.ifmemorygame.model.TeacherList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://apiprofessoresifpbtsi.herokuapp.com/"

interface TeacherService {

    @GET("professores")
    fun list(@Query("page") page: Int): Call<TeacherList>

    companion object{
        operator fun invoke () : TeacherService {
            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TeacherService::class.java)
        }
    }

}