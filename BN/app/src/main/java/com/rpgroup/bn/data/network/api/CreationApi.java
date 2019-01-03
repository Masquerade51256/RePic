package com.rpgroup.bn.data.network.api;

import com.rpgroup.bn.model.PersonalPic;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface CreationApi {
    //获取特定用户名的信息
    @FormUrlEncoded
    @POST("creation/insertCreation")
   Observable<List<PersonalPic>> InsertCreation(@Field("name") String name, @Field("url") String url);

    @FormUrlEncoded
    @POST("creation/findByName")
    Observable<List<PersonalPic>> findByName(@Field("name") String name);

    @FormUrlEncoded
    @POST("creation")
    Observable<List<PersonalPic>> findAllCreation();

}
