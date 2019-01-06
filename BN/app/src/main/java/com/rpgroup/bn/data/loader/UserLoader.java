package com.rpgroup.bn.data.loader;

import com.rpgroup.bn.data.network.api.UserApi;
import com.rpgroup.bn.data.network.provider.RetrofitServiceManager;
import com.rpgroup.bn.model.User;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

//把请求逻辑封装在在一个业务Loader 里面，一个Loader里面可以处理多个 Api 接口
public class UserLoader extends ObjectLoader {
  private final UserApi mUserApi;

  public UserLoader() {
    mUserApi = RetrofitServiceManager.getInstance().create(UserApi.class);
  }

  public Observable<User> getUser(String name) {
    return observe(mUserApi.getUser(name))
        .map(new Function<User, User>() {
          @Override
          public User apply(User user) {
            return user;
          }
        });
  }

  public Observable<User> insertUser(String name,String password) {
    return observe(mUserApi.InsertUser(name, password))
        .map(new Function<User, User>() {
          @Override
          public User apply(User user) throws Exception {
            return user;
          }
        });
  }
}

