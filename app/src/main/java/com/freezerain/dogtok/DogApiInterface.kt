package com.freezerain.dogtok

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

internal interface DogApiInterface {
    //@GET("/api/unknown")
    //Call<MultipleResource> doGetListResources();
    //
    //@POST("/api/users")
    //Call<User> createUser(@Body User user);
    //
    //@GET("/api/users?")
    //Call<UserList> doGetUserList(@Query("page") String page);
    //
    //@FormUrlEncoded
    //@POST("/api/users?")
    //Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
    @get:GET("/api/breeds/image/random")
    val dogUrl: Call<DogApiModel?>?

    @GET("/api/breeds/image/random")
    suspend fun nextUrl(): Response<DogApiModel>

}