package com.rpgroup.bn.view.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.rpgroup.bn.presenter.BasePresenter;

//抽象出解绑和绑定操作
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends Activity {
  private P presenter;
  private V view;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if(this.presenter == null){
      this.presenter = createPresenter();
    }
    if(this.view==null){
      this.view = createView();
    }

    if(this.presenter != null && this.view != null){
      this.presenter.attachView(this.view);
    }
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    if(this.presenter != null && this.view != null){
      this.presenter.detachView();
    }
  }

  public P getPresenter(){
    return presenter;
  }

  public abstract P createPresenter();
  public abstract V createView();
}

