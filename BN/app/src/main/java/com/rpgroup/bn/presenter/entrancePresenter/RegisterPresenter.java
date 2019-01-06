package com.rpgroup.bn.presenter.entrancePresenter;

import android.util.Log;
import com.rpgroup.bn.model.User;
import com.rpgroup.bn.data.loader.UserLoader;
import com.rpgroup.bn.presenter.BasePresenter;
import com.rpgroup.bn.presenter.MD5Util;
import com.rpgroup.bn.view.entrance.RegisterView;
import io.reactivex.functions.Consumer;

public class RegisterPresenter extends BasePresenter<RegisterView> {
  private UserLoader mUserLoader;

  private boolean nameExisted;

  public void setNameExisted(boolean nameExisted) { this.nameExisted = nameExisted; }

  public RegisterPresenter() {
    this.mUserLoader = new UserLoader();
  }

  public void checkRegister(String name, String password, String password1){
    String md5Password = MD5Util.MD5(password);
    if(getView() != null){

      if(name.isEmpty()|| password.isEmpty()||password1.isEmpty()){
        getView().onRegisterResult(false,name,"请输入完整信息");
      }

      else if (ifNameExisted(name)){
        getView().onRegisterResult(false,name,"用户名已存在，请重新输入");
      }

      else if (!password.equals(password1)){
        getView().onRegisterResult(false,name,"密码输入不一致，请重新输入");
      }

      else {
        okToRegister(name,md5Password);
      }
    }
  }

  private boolean ifNameExisted(final String name) {
    this.mUserLoader.getUser(name).subscribe(new Consumer<User>() {
      @Override
      public void accept(User user) {
        setNameExisted(true);
      }
    }, new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
        setNameExisted(false);
        Log.e("TAG","error message:"+throwable.getMessage());
      }
    });
    return nameExisted;
  }

  private void okToRegister(final String name, String password){
    this.mUserLoader.insertUser(name,password).subscribe(new Consumer<User>() {
      @Override
      public void accept(User user) throws Exception {
        if(name.equals(user.getName()))
        {
          getView().onRegisterResult(true,name,"注册成功");
        }
      }
    }, new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
        Log.e("TAG","error message:"+throwable.getMessage());
        getView().onRegisterResult(false,name,"注册失败，请重试");
      }
    });
  }
}
