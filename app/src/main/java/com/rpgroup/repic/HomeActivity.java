package com.rpgroup.repic;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

  private PopupWindow mPopWindow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_info);

    Intent intent = getIntent();

    ImageButton button = findViewById(R.id.btn_comment);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showPopupWindow();
      }
    });
  }

  private void showPopupWindow() {
    //设置contentView
    View contentView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.popup_comment, null);

    mPopWindow = new PopupWindow(contentView,
        ActionBar.LayoutParams.MATCH_PARENT,
        ActionBar.LayoutParams.WRAP_CONTENT, true);
    mPopWindow.setContentView(contentView);

    //防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
//        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
    mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    //设置软键盘弹出
    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(
        Context.INPUT_METHOD_SERVICE);
    inputMethodManager.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);//这里给它设置了弹出的时间

    //设置各个控件的点击响应
    final EditText editText = contentView.findViewById(R.id.pop_editText);
    Button btn = contentView.findViewById(R.id.pop_btn);

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String inputString = editText.getText().toString();
        Toast.makeText(HomeActivity.this, inputString, Toast.LENGTH_SHORT).show();
        mPopWindow.dismiss();//让PopupWindow消失
      }
    });
    //是否具有获取焦点的能力
    mPopWindow.setFocusable(true);
    //显示PopupWindow
    View rootView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.home_info, null);
    mPopWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
  }
}
