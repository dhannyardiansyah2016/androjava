package com.example.latihanretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("retrofit/POST/readcontacts.php")
    Call<List<Contact>> getContact();

    @FormUrlEncoded
    @POST("retrofit/POST/addcontact.php")
    public Call<Contact> insertUser(
            @Field("name") String name,
            @Field("email") String mail
    );

    @FormUrlEncoded
    @POST("retrofit/POST/editcontact.php")
    public Call<Contact> updateUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("email") String mail
    );

}
