package com.rpgroup.bn.view.listener;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import com.rpgroup.bn.R;

public class favoriteListener implements View.OnClickListener {
    boolean f=false;
    private ImageButton btnLike;

    private favoriteListener(ImageButton btn){
        btnLike = btn;
    }


    public static favoriteListener getFavoriteListener(ImageButton btn){
        Log.i("mylikebuttom", "getFavoriteListener: init");
        return new favoriteListener(btn);
    }

    @Override
    public void onClick(View v) {
        Log.i("mylikebuttom", "getFavoriteListener: click");
        if(f){
            btnLike.setImageResource(R.drawable.ic_favorite);
            f=false;
        }
        else {
            btnLike.setImageResource(R.drawable.ic_favorited);
            f=true;
        }

    }
}
