package com.rpgroup.bn.presenter;

import com.rpgroup.bn.view.base.BaseView;

public abstract class BasePresenter <V extends BaseView> {

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