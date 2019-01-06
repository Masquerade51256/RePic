package com.rpgroup.bn.data.network.api;

import com.rpgroup.bn.model.PersonalPic;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.MultipartBody;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CreationApi {
  //获取特定作品的信息
  //*************增加一条作品记录**********
  @Multipart
  @POST("creation/insertCreation")
  Observable<List<PersonalPic>> uploadCreation(@Field("name") String name,
      @Part("file") MultipartBody.Part file);

  //*************按照名字查找作品**********
  @FormUrlEncoded
  @POST("creation/findByName")
  Observable<List<PersonalPic>> findByName(@Field("name") String name);

  //*************获取全部作品**********
  @GET("creation")
  Observable<List<PersonalPic>> findAllCreation();

}
