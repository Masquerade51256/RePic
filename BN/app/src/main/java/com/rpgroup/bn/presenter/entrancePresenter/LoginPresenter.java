package com.rpgroup.bn.presenter.entrancePresenter;

import android.util.Log;
import com.rpgroup.bn.model.User;
import com.rpgroup.bn.data.loader.UserLoader;
import com.rpgroup.bn.presenter.BasePresenter;
import com.rpgroup.bn.presenter.MD5Util;
import com.rpgroup.bn.view.entrance.LoginView;
import io.reactivex.functions.Consumer;

public class LoginPresenter extends BasePresenter<LoginView> {

  private UserLoader mUserLoader;

  public LoginPresenter() {
    this.mUserLoader = new UserLoader();
  }

  public void checkLogin(final String name, final String password) {
    final String md5Password = MD5Util.MD5(password);
    if(getView() != null){
      if (name.isEmpty() || password.isEmpty()) {
        getView().onLoginResult(false, name, "请输入完整信息");
      }
      else {
        this.mUserLoader.getUser(name).subscribe(new Consumer<User>() {
          @Override
          public void accept(User user) {
            if (md5Password.equals(user.getPassword())) {
              getView().onLoginResult(true, name, "登录成功");
            }
            else { getView().onLoginResult(false, name, "密码错误"); }
          }
          }, new Consumer<Throwable>() {
          @Override
          public void accept(Throwable throwable) throws Exception {
            Log.e("TAG", "error message:" + throwable.getMessage());
            getView().onLoginResult(false, name, "用户不存在");
          }
        });
      }
    }
  }

}
