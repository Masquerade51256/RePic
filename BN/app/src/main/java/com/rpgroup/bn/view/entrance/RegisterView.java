package com.rpgroup.bn.view.entrance;

import com.rpgroup.bn.view.base.BaseView;

public interface RegisterView extends BaseView {
  void onRegisterResult(boolean success,String userName, String result);
}
