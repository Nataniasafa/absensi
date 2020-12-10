package com.natania.uas.remote;

import com.natania.uas.model.PersonItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface DataService {
    @GET("person/get/")
    Call<List<PersonItem>> getData();

    @FormUrlEncoded
    @POST("person/add")
    Call<PersonItem> addData(@Field("name") String name,
                                @Field("date") String date,
                                @Field("class")String kelas,
                                @Field("lesson")String lesson,
                                @Field("desc")String desc);


    @FormUrlEncoded
    @PUT("person/update/")
    Call<PersonItem> updateData(@Field("id") int id,
                                   @Field("name") String name,
                                   @Field("date") String date,
                                   @Field("class")String kelas,
                                   @Field("lesson")String lesson,
                                   @Field("desc")String desc);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = ("person/delete/"), hasBody = true)
    Call<PersonItem> deleteProduct(@Field("id") int id);

}
