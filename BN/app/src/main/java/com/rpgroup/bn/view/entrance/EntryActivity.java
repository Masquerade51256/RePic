package com.rpgroup.bn.view.entrance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.rpgroup.bn.view.navigation.NavigationActivity;
import com.rpgroup.bn.R;
import com.rpgroup.bn.presenter.common.UserSharedPreferenceConfig;
import com.rpgroup.bn.presenter.entrancePresenter.LoginPresenter;
import com.rpgroup.bn.view.base.BaseActivity;

public class EntryActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView {
  private TextInputEditText name_input;
  private TextInputEditText password_input;
  private Button btn_login;
  private Button btn_signUp;
  private SharedPreferences mSharedPreferences;
  private boolean isFirstUsed = false;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    if (getIntent()!= null) {
      Intent intent = getIntent();
    }

    //初始化控件
    initUI();

    //初始化数据
    initData();

  }

  private void initUI() {
    name_input = findViewById(R.id.name_input);
    password_input = findViewById(R.id.password_input);
    btn_login = findViewById(R.id.logIn_btn);
    btn_signUp = findViewById(R.id.signUp_btn);

    /*===================登录按钮监听器===================*/
    btn_login.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = name_input.getText().toString();
        String password = password_input.getText().toString();

        //实例化SharedPreferences对象
        if (mSharedPreferences == null) {
          mSharedPreferences = getApplicationContext().getSharedPreferences(
              UserSharedPreferenceConfig.SP_USER_CONFIG, Context.MODE_PRIVATE);
        }

        getPresenter().checkLogin(mSharedPreferences,name,password,isFirstUsed);
      }
    });

    /*==================注册按钮监听器====================*/
    btn_signUp.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(EntryActivity.this, RegisterActivity.class);
        startActivity(intent);
        EntryActivity.this.finish();
      }
    });

    name_input.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        //isFirstUsed = true;
      }

      @Override
      public void afterTextChanged(Editable s) {
        isFirstUsed = true;
      }
    });

    password_input.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        isFirstUsed = true;
      }

      @Override
      public void afterTextChanged(Editable s) {
        isFirstUsed = true;
      }
    });

  }

  private void initData() {
    //实例化SharedPreferences对象
    if (mSharedPreferences == null) {
      mSharedPreferences = getApplicationContext().getSharedPreferences(
          UserSharedPreferenceConfig.SP_USER_CONFIG, Context.MODE_PRIVATE);
    }

    name_input.setText(mSharedPreferences.getString(UserSharedPreferenceConfig.SP_NAME, ""));
    password_input.setText(
        mSharedPreferences.getString(UserSharedPreferenceConfig.SP_MD5PASSWORD, ""));

    isFirstUsed = name_input.getText() == null;

  }

  @Override
  public LoginPresenter createPresenter() {
    return new LoginPresenter();
  }

  @Override
  public LoginView createView() {
        return this;
    }

  @Override
  public void onLoginResult(boolean success, String userName, String result) {
    Toast.makeText(EntryActivity.this,result,Toast.LENGTH_LONG).show();
    if (success) {
      Intent intent = new Intent(EntryActivity.this, NavigationActivity.class);
      intent.putExtra("userName",userName);
      startActivity(intent);
      EntryActivity.this.finish();
    }
  }
}
