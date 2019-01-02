package com.rpgroup.bn.view.entrance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.rpgroup.bn.DemoActivity;
import com.rpgroup.bn.R;
import com.rpgroup.bn.presenter.entrancePresenter.RegisterPresenter;
import com.rpgroup.bn.view.base.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterView,RegisterPresenter> implements RegisterView  {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    Intent intent = getIntent();

    final TextInputEditText name_input = findViewById(R.id.name_input_register);
    final TextInputEditText password_input = findViewById(R.id.password_input_register);
    final TextInputEditText password1_input = findViewById(R.id.password1_input_register);
    Button btn_signUp = findViewById(R.id.signUp_btn_register);
    Button btn_login = findViewById(R.id.logIn_btn_register);

    /*==================注册按钮监听器====================*/
    btn_signUp.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = name_input.getText().toString();
        String password = password_input.getText().toString();
        String password1 = password1_input.getText().toString();
        getPresenter().checkRegister(name, password, password1);
      }
    });

    /*==================注册按钮监听器====================*/
    btn_login.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(RegisterActivity.this, EntryActivity.class);
        startActivity(intent);
        RegisterActivity.this.finish();
      }
    });

  }

  @Override
  public RegisterPresenter createPresenter() {
    return new RegisterPresenter();
  }

  @Override
  public RegisterView createView() {
    return this;
  }

  @Override
  public void onRegisterResult(boolean success, String userName, String result) {
    Toast.makeText(RegisterActivity.this,result,Toast.LENGTH_LONG).show();
    if(success){
      Intent intent = new Intent(RegisterActivity.this, EntryActivity.class);
      startActivity(intent);
      RegisterActivity.this.finish();
    }
  }
}

