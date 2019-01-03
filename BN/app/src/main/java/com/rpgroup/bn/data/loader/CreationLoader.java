package com.rpgroup.bn.data.loader;

import com.rpgroup.bn.data.network.api.CreationApi;
import com.rpgroup.bn.data.network.provider.RetrofitServiceManager;
import com.rpgroup.bn.model.PersonalPic;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.List;


public class CreationLoader extends ObjectLoader {
    private CreationApi mcreationApi;

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

    public Observable<List<PersonalPic>> insertCreation(String name,String url) {
        return observe(mcreationApi.InsertCreation(name,url))
                .map(new Function<List<PersonalPic>, List<PersonalPic>>() {
                    @Override
                    public List<PersonalPic> apply(List<PersonalPic> creation) {
                        return creation;
                    }
                });

    }

    public Observable<List<PersonalPic>> findAllCreation(){
      return observe(mcreationApi.findAllCreation())
          .map(new Function<List<PersonalPic>, List<PersonalPic>>() {
            @Override
            public List<PersonalPic> apply(List<PersonalPic> personalPics)  {
              return personalPics;
            }
          });
    }
}

