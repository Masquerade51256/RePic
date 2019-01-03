  package com.rpgroup.bn.data.network;

  import android.annotation.SuppressLint;
  import android.util.Log;
  import com.rpgroup.bn.data.network.api.UploadInterface;
  import com.rpgroup.bn.data.network.provider.RetrofitServiceManager;
  import io.reactivex.android.schedulers.AndroidSchedulers;
  import io.reactivex.functions.Consumer;
  import io.reactivex.schedulers.Schedulers;
  import okhttp3.MediaType;
  import okhttp3.MultipartBody;
  import okhttp3.RequestBody;
  import okhttp3.ResponseBody;

  import java.io.File;


  public class UploadUtil {
    private static UploadInterface mNetService;
    @SuppressLint("CheckResult")
    public static void upLoad2Server(File file) {
      mNetService=RetrofitServiceManager.getInstance().create(UploadInterface.class);
      RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
      MultipartBody.Part body = MultipartBody.Part.createFormData("liuyuehan.jpg", file.getName(), requestBody);

      Log.i("uploadFile", "111111111");
      mNetService.uploadImg(body)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(
          new Consumer<ResponseBody>(){
        @Override
        public void accept(ResponseBody responseBody){
          Log.i("uploadFile", "222222222");
        }
      },new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
          Log.i("uploadFile", "nononononono");
        }
      });
    }
  }

