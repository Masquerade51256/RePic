package com.rpgroup.bn.view.entrance;

import com.rpgroup.bn.view.base.BaseView;

public interface LoginView extends BaseView {
  void onLoginResult(boolean success,String userName, String result);
}

