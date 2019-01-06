package com.rpgroup.bn.data.loader;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 将一些重复的操作提出来，放到父类以免Loader 里每个接口都有重复代码
 *The network request should be running on the I/O thread,
 *and we should observe on the Android main thread (because we are changing the view in callback).
 */
public class ObjectLoader {
  protected  <T> Observable<T> observe(Observable<T> observable) {
    return observable
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}

