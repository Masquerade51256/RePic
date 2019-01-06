package com.rpgroup.bn.presenter.entrancePresenter;

import android.content.SharedPreferences;
import android.util.Log;
import com.rpgroup.bn.model.InfoConfig;
import com.rpgroup.bn.data.loader.UserLoader;
import com.rpgroup.bn.model.User;
import com.rpgroup.bn.presenter.common.BasePresenter;
import com.rpgroup.bn.presenter.common.MD5Util;
import com.rpgroup.bn.presenter.common.UserSharedPreferenceConfig;
import com.rpgroup.bn.view.entrance.LoginView;
import io.reactivex.functions.Consumer;

//登录的presenter
public class LoginPresenter extends BasePresenter<LoginView> {

  private final UserLoader mUserLoader;

  public LoginPresenter() {
    this.mUserLoader = new UserLoader();
  }

  //************判断是否可以登录************8
  public void checkLogin(final SharedPreferences sharedPreferences,final String name,
      final String password,boolean isFirstUsed) {

    final String md5Password;
    //第一次用时，先给密码加密
    if (isFirstUsed) {
      md5Password = MD5Util.MD5(password);
    } else {
      md5Password = password;
    }

    if (getView() != null) {
      //判断用户输入是否完整
      if (name.isEmpty() || password.isEmpty()) {
        getView().onLoginResult(false, name, "请输入完整信息");
      }
      //判断用户密码是否正确
      else {
        this.mUserLoader.getUser(name).subscribe(new Consumer<User>() {
          @Override
          public void accept(User user) {
            if (md5Password.equals(user.getPassword())) {
              InfoConfig.setUserName(name);
              setUserToSharedPreferences(sharedPreferences,name,md5Password);
              getView().onLoginResult(true, name, "登录成功");
            } else { getView().onLoginResult(false, name, "密码错误"); }
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

  //登录成功，则将用户名、密码存储到shared preference里面
  private void setUserToSharedPreferences(SharedPreferences sharedPreferences,
      String name,String password) {
    //实例化SharedPreferences对象的编辑者对象
    SharedPreferences.Editor editor = sharedPreferences.edit();

    //存储数据
    editor.putString(UserSharedPreferenceConfig.SP_NAME,name);
    editor.putString(UserSharedPreferenceConfig.SP_MD5PASSWORD,password);

    //提交
    editor.apply();
  }
}
