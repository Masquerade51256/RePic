package com.rpgroup.bn.data.network;

import com.rpgroup.bn.model.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
  //获取特定用户名的信息
  @FormUrlEncoded
  @POST("user/findByName")
  Observable<User> getUser(@Field("name") String name);

  @FormUrlEncoded
  @POST("user/insertUser")
  Observable<User> InsertUser(@Field("name") String name, @Field("password") String password);
}
