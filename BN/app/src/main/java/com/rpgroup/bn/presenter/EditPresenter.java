package com.rpgroup.bn.presenter;

import android.util.Log;
import com.rpgroup.bn.data.loader.CreationLoader;

import com.rpgroup.bn.model.PersonalPic;
import com.rpgroup.bn.view.fragment.EditView;
import io.reactivex.functions.Consumer;

import java.util.List;

public class EditPresenter  extends BasePresenter<EditView> {
    private CreationLoader mCreationLoader;

    public EditPresenter() {
        this.mCreationLoader = new CreationLoader();
    }

    public void checkSaveImg(String name, String url) {
        this.mCreationLoader.insertCreation(name,url).subscribe(new Consumer<List<PersonalPic>>() {
            @Override
            public void accept(List<PersonalPic> creations) {
                getView().onSaveResult(true,"上传成功");
            }
        },new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("TAG", "error message:" + throwable.getMessage());
                getView().onSaveResult(false,  "上传失败，请重试");
            }
        });
    }
}
