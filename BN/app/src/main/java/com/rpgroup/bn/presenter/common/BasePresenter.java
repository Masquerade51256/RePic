package com.rpgroup.bn.presenter.common;

import com.rpgroup.bn.view.base.BaseView;

//presenter基类
public abstract class BasePresenter<V extends BaseView> {

  private V view;

  public  V getView() {
    return view;
  }

  public void attachView(V view) {
    this.view = view;
  }

  public void detachView() {
    this.view = null;
  }
}