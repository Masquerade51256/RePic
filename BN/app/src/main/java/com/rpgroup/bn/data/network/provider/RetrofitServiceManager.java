package com.rpgroup.bn.data.network.provider;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//每一个请求，都需要一个接口，
//里面定义了请求方法和请求参数等等，
//而获取接口实例需要通过一个Retrofit实例
public class RetrofitServiceManager {
  private static final int DEFAULT_TIME_OUT = 60;//超时时间 5s

  private static final int DEFAULT_READ_TIME_OUT = 60;

  private final Retrofit mRetrofit;

  private RetrofitServiceManager() {
    // 创建 OKHttpClient
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
    builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
    builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间

    // 创建Retrofit
    mRetrofit = new Retrofit.Builder()
        .baseUrl(ApiConfig.BASE_URL)
        .client(builder.build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  private static class SingletonHolder {
    private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
  }

  /**
   * 获取RetrofitServiceManager
   * @return
   */
  public static RetrofitServiceManager getInstance() {
    return SingletonHolder.INSTANCE;
  }

  /**
   * 获取对应的Service
   * @param service Service 的 class
   * @param <T>
   * @return
   */
  public <T> T create(Class<T> service) {
    return mRetrofit.create(service);
  }
}

