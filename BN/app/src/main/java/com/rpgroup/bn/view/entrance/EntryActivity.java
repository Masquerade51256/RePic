package com.rpgroup.bn.view.entrance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.rpgroup.bn.DemoActivity;
import com.rpgroup.bn.R;
import com.rpgroup.bn.presenter.entrancePresenter.LoginPresenter;
import com.rpgroup.bn.view.base.BaseActivity;

public class EntryActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView {

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if(getIntent()!=null){
      Intent intent = getIntent();
    }

    final TextInputEditText name_input = findViewById(R.id.name_input);
    final TextInputEditText password_input = findViewById(R.id.password_input);
    Button btn_login = findViewById(R.id.logIn_btn);
    Button btn_signUp = findViewById(R.id.signUp_btn);

    /*===================登录按钮监听器===================*/
    btn_login.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = name_input.getText().toString();
        String password = password_input.getText().toString();
        getPresenter().checkLogin(name,password);
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
      if(success){
        Intent intent = new Intent(EntryActivity.this, DemoActivity.class);
        intent.putExtra("userName",userName);
        startActivity(intent);
        EntryActivity.this.finish();
      }
    }
}
