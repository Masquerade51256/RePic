package com.rpgroup.bn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EntryActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_login=findViewById(R.id.logIn_btn);


        btn_login.setOnClickListener(listener);
    }
    Button.OnClickListener listener=new Button.OnClickListener(){
        public void onClick(View v) {
            Intent intent = new Intent(EntryActivity.this,DemoActivity.class);
            startActivity(intent);
            EntryActivity.this.finish();
        }

    };
}
