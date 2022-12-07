package ca.unb.mobiledev.project

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("getStudentInformation")
    fun getStudentInformation(@Body credential: Credential): Call<Student?>?

    @POST("getDriverInformation")
    fun getDriverInformation(@Body credential: Credential): Call<Driver?>?
}