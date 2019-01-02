package com.rpgroup.bn.data.loader;

import com.rpgroup.bn.data.network.UserApi;
import com.rpgroup.bn.data.network.provider.RetrofitServiceManager;
import com.rpgroup.bn.model.User;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserLoader extends ObjectLoader{
  private UserApi mUserApi;

  public UserLoader(){
    mUserApi = RetrofitServiceManager.getInstance().create(UserApi.class);
  }

  public Observable<User> getUser(String name){
    return observe(mUserApi.getUser(name))
        .map(new Function<User, User>() {
          @Override
          public User apply (User user) {
            return user;
          }
        });
  }

  public Observable<User> insertUser(String name,String password){
    return observe(mUserApi.InsertUser(name, password))
        .map(new Function<User, User>() {
          @Override
          public User apply(User user) throws Exception {
            return user;
          }
        });
  }
}

