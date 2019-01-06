package com.rpgroup.bn.view.entrance;

import com.rpgroup.bn.view.base.BaseView;

public interface LoginView extends BaseView {
  //登录结果显示给用户
  void onLoginResult(boolean success,String userName, String result);
}

