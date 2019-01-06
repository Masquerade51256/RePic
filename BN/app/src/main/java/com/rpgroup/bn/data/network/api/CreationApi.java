package com.rpgroup.bn.data.network.api;

import com.rpgroup.bn.model.PersonalPic;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.io.File;
import java.util.List;

public interface CreationApi {
    //获取特定用户名的信息
    //@FormUrlEncoded
    @Multipart
    @POST("creation/insertCreation")
    Observable<List<PersonalPic>> uploadCreation(@Field("name") String name, @Part("file") MultipartBody.Part file);

    @FormUrlEncoded
    @POST("creation/findByName")
    Observable<List<PersonalPic>> findByName(@Field("name") String name);
    
    @GET("creation")
    Observable<List<PersonalPic>> findAllCreation();

}
