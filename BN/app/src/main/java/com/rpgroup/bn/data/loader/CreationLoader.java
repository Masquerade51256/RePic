package com.rpgroup.bn.data.loader;

import android.annotation.SuppressLint;
import android.util.Log;
import com.rpgroup.bn.data.InfoConfig;
import com.rpgroup.bn.data.network.api.CreationApi;
import com.rpgroup.bn.data.network.provider.RetrofitServiceManager;
import com.rpgroup.bn.model.PersonalPic;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.File;
import java.util.List;


public class CreationLoader extends ObjectLoader {
    public CreationApi mcreationApi;

    public CreationLoader() {
        mcreationApi = RetrofitServiceManager.getInstance().create(CreationApi.class);
    }

    public Observable<List<PersonalPic>> findByName(String name) {
        return observe(mcreationApi.findByName(name))
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
        Log.i("myupload", "half success "+InfoConfig.getUserName());
        Log.i("myupload", "half success "+String.valueOf(file));
        Log.i("myupload", "half success "+String.valueOf(requestBody));
        Log.i("myupload", "half success "+String.valueOf(body));
        return observe(mcreationApi.uploadCreation(InfoConfig.getUserName(),body))
                .map(new Function<List<PersonalPic>, List<PersonalPic>>() {
                    @Override
                    public List<PersonalPic> apply(List<PersonalPic> personalPics){
                        Log.i("myupload", "3 quarters success");
                        return personalPics;
                    }
                });
    }

    public Observable<List<PersonalPic>> findAllCreation() {
        return observe(mcreationApi.findAllCreation())
                .map(new Function<List<PersonalPic>, List<PersonalPic>>() {
                    @Override
                    public List<PersonalPic> apply(List<PersonalPic> creations) {
                        return creations;
                    }
                });
    }

}