package com.rpgroup.bn.view.entrance;

import com.rpgroup.bn.view.base.BaseView;

public interface RegisterView extends BaseView {
  //注册结果显示给用户
  void onRegisterResult(boolean success,String userName, String result);
}
