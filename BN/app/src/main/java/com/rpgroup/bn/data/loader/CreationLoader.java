package com.rpgroup.bn.data.loader;

import android.util.Log;

import com.rpgroup.bn.data.network.api.CreationApi;
import com.rpgroup.bn.data.network.provider.RetrofitServiceManager;
import com.rpgroup.bn.model.InfoConfig;

import com.rpgroup.bn.model.PersonalPic;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


//把请求逻辑封装在在一个业务Loader 里面，一个Loader里面可以处理多个 Api 接口
public class CreationLoader extends ObjectLoader {
  public final CreationApi mCreationApi;

  public CreationLoader() {
    mCreationApi = RetrofitServiceManager.getInstance().create(CreationApi.class);
  }

  public Observable<List<PersonalPic>> findByName(String name) {
    return observe(mCreationApi.findByName(name))
        .map(new Function<List<PersonalPic>, List<PersonalPic>>() {
          @Override
          public List<PersonalPic> apply(List<PersonalPic> creations) {
            return creations;
          }
        });
  }

  public Observable<List<PersonalPic>> uploadCreation(File file) {
    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    MultipartBody.Part body = MultipartBody.Part.createFormData("file", "file", requestBody);
    Log.i("myUpload", "half success " + InfoConfig.getUserName());
    Log.i("myUpload", "half success " + String.valueOf(file));
    Log.i("myUpload", "half success " + String.valueOf(requestBody));
    Log.i("myUpload", "half success " + String.valueOf(body));
    return observe(mCreationApi.uploadCreation(InfoConfig.getUserName(),body))
        .map(new Function<List<PersonalPic>, List<PersonalPic>>() {
          @Override
          public List<PersonalPic> apply(List<PersonalPic> personalPics) {
            Log.i("myUpload", "3 quarters success");
            return personalPics;
          }
        });
  }

  public Observable<List<PersonalPic>> findAllCreation() {
    return observe(mCreationApi.findAllCreation())
        .map(new Function<List<PersonalPic>, List<PersonalPic>>() {
          @Override
          public List<PersonalPic> apply(List<PersonalPic> creations) {
            return creations;
          }
        });
  }
}