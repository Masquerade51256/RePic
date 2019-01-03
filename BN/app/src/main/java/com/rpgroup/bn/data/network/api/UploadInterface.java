package com.rpgroup.bn.data.network.api;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadInterface {
  @Multipart
  @POST("uploadImg")
  Observable<ResponseBody> uploadImg(@Part MultipartBody.Part img);
}
