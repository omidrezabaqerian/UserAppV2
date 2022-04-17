package ir.omidrezabagherian.userapp.data.network

import ir.omidrezabagherian.userapp.model.CreateUser
import ir.omidrezabagherian.userapp.model.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserServices {
    @POST("users")
    fun createUser(@Body body: CreateUser): Call<String>

    @Multipart
    @POST("users/{userId}/image")
    fun uploadImageUser(
        @Path("userId") id: String,
        @Part image: MultipartBody.Part
    ): Call<Any>

    @GET("users")
    fun getUser(): Call<List<User>>

    @GET("users")
    fun getUserByNationalCode(@Query("nationalCode") nationalCode: String): Call<List<User>>

    @GET("users")
    fun getUserByFirstName(@Query("firstName") firstName: String): Call<List<User>>

    @GET("users")
    fun getUserByLastName(@Query("lastName") lastName: String): Call<List<User>>

    @GET("users")
    fun getUserByNationalCodeAndFirstName(
        @Query("nationalCode") nationalCode: String,
        @Query("firstName") firstName: String
    ): Call<List<User>>

    @GET("users")
    fun getUserByNationalCodeAndLastName(
        @Query("nationalCode") nationalCode: String,
        @Query("lastName") lastName: String
    ): Call<List<User>>

    @GET("users")
    fun getUserByFirstNameAndLastName(
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String
    ): Call<List<User>>

    @GET("users")
    fun getUserByNationalCodeAndFirstNameAndLastName(
        @Query("nationalCode") nationalCode: String,
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String
    ): Call<List<User>>
}