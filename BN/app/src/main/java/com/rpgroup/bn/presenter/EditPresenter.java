package com.rpgroup.bn.presenter;

import android.util.Log;
import com.rpgroup.bn.data.loader.CreationLoader;

import com.rpgroup.bn.model.PersonalPic;
import com.rpgroup.bn.view.fragment.EditView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.File;
import java.util.List;

public class EditPresenter  extends BasePresenter<EditView> {
    private CreationLoader mCreationLoader;

    public EditPresenter() {
        this.mCreationLoader = new CreationLoader();
    }

    public void checkSaveImg(String name, File file) {
        Log.i("myupload", "upload begin");
        this.mCreationLoader
                .uploadCreation(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PersonalPic>>() {
            @Override
            public void accept(List<PersonalPic> responseBody) {
                Log.i("myupload", "success");
                getView().onSaveResult(true,"上传成功");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i("myupload", "fail");
                getView().onSaveResult(false,  "上传失败，请重试");
            }
        });
        Log.i("myupload", "upload over");
    }
}
