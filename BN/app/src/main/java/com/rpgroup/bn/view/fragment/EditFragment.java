package com.rpgroup.bn.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.glidebitmappool.GlideBitmapPool;
import com.rpgroup.bn.R;
import com.rpgroup.bn.model.InfoConfig;
import com.rpgroup.bn.presenter.pagePresenter.EditPresenter;
import com.rpgroup.bn.view.base.BaseFragment;
import com.rpgroup.bn.view.common.GetPhotoFromGallery;
import com.rpgroup.bn.view.common.MoveComponent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class EditFragment extends BaseFragment<EditView,EditPresenter>
    implements EditView,View.OnClickListener, EasyPermissions.PermissionCallbacks {
  private ImageView mImageView;
  private File savePath;//照片及图片存储
  private Uri uri;
  private final String[] permissions = {Manifest.permission.CAMERA,
      Manifest.permission.WRITE_EXTERNAL_STORAGE};
  private ViewGroup ivHolder;
  private Button btnGetPicFromCamera , btnCropPic;
  private Button btnGetPicFromPhotoAlbum , btnSplice, btnSaveNUpload;
  private static int imageCount = 0;
  private View view;
  private Bitmap mBitmap = null;

  @Override
  public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.page_edit, container, false);
    mImageView = view.findViewById(R.id.iv_show);
    ivHolder = view.findViewById(R.id.img_holder);
    btnCropPic = view.findViewById(R.id.btn_crop);
    btnGetPicFromCamera = view.findViewById(R.id.btn_camera);
    btnGetPicFromPhotoAlbum = view.findViewById(R.id.btn_gallery);
    btnSplice = view.findViewById(R.id.btn_splice);
    btnSaveNUpload = view.findViewById(R.id.btn_save_n_upload);

    init();
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    getPresenter().detachView();
  }

  public void init() {
    MoveComponent.setLastMc(null);
    getPermission();
    btnCropPic.setEnabled(false);
    btnSplice.setEnabled(false);
    btnSaveNUpload.setEnabled(false);
    btnSaveNUpload.setEnabled(false);
    btnCropPic.setOnClickListener(this);
    btnGetPicFromCamera.setOnClickListener(this);
    btnGetPicFromPhotoAlbum.setOnClickListener(this);
    btnSplice.setOnClickListener(this);
    btnSaveNUpload.setOnClickListener(this);
  }

  /*****点击事件监听*****/
  @Override
  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.btn_camera://拍照
        goCamera();
        btnCropPic.setEnabled(true);
        btnSaveNUpload.setEnabled(true);
        break;
      case R.id.btn_gallery://相册选取照片
        goPhotoAlbum();
        btnCropPic.setEnabled(true);
        btnSaveNUpload.setEnabled(true);
        break;
      case R.id.btn_crop://裁剪照片
        if (crop2()) {
          btnGetPicFromCamera.setEnabled(false);
          btnCropPic.setEnabled(false);
          btnGetPicFromPhotoAlbum.setEnabled(false);
          btnSplice.setEnabled(true);
          btnSaveNUpload.setEnabled(false);
        }
        break;
      case R.id.btn_splice://拼合照片
          btnGetPicFromCamera.setEnabled(true);
          btnCropPic.setEnabled(true);
          btnGetPicFromPhotoAlbum.setEnabled(true);
          btnSplice.setEnabled(false);
          btnSaveNUpload.setEnabled(true);
        break;
      case R.id.btn_save_n_upload://保存并上传照片
        try {
          //保存
          saveImg(mBitmap);
        } catch (Exception e) {
          Log.i(TAG, "onClick: exception");
        }
        Log.i("myUpload", "saveImg: " + String.valueOf(savePath));
        //上传
        getPresenter().checkSaveImg(InfoConfig.getUserName(), savePath);
        Log.i("myUpload", "finish");
        break;
      default:
        break;
    }
  }

    /*****获得相机相册权限*****/
    private void getPermission() {
        if (EasyPermissions.hasPermissions(getContext(), permissions)) {
            //已经打开权限
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }
    }

    /*****打开手机相册*****/
    private void goPhotoAlbum() {
      Intent intent = new Intent();
      intent.setAction(Intent.ACTION_PICK);
      intent.setType("image/*");
      startActivityForResult(intent, 2);
    }

    /*****文件保存及上传*****/
    private void saveImg(Bitmap bm) throws IOException {
      updatePath("create");
      File myCaptureFile = savePath;
      if (!myCaptureFile.exists()) {
        myCaptureFile.createNewFile();
      }
      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
      bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
      bos.flush();
      bos.close();
      Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
      Uri uri = Uri.fromFile(myCaptureFile);
      intent.setData(uri);
      getContext().sendBroadcast(intent);

      Log.i("save", "saveImg: " + InfoConfig.getUserName());
      Log.i("save", "saveImg: " + String.valueOf(savePath));
    }

    /*****打开相机*****/
    private void goCamera() {
      updatePath("camera");
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        uri = FileProvider
            .getUriForFile(getContext(), "com.rpgroup.bn.fileprovider", savePath);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); } else {
        uri = Uri.fromFile(savePath);
      }
      intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
      startActivityForResult(intent, 1);
    }

    /*****裁剪图片为左右两半*****/
    private boolean crop2(){
      //可拖动进度条调节裁剪位置
      SeekBar sbVertical = view.findViewById(R.id.sb_vertical);
      sbVertical.setMax(100);
      //计算及保存裁剪后位置
      int ivX, ivY, ivW, ivH, pX, cutLeftWidth;
      ivX = (int) mImageView.getX();
      ivY = (int) mImageView.getY();
      pX = sbVertical.getWidth() * sbVertical.getProgress() / 100;
      mImageView.setDrawingCacheEnabled(true);//提取ImageView中的Bitmap
      if (mImageView.getDrawingCache()==null) {
        return false;
      }
      Bitmap srcBitmap = Bitmap.createBitmap(mImageView.getDrawingCache());
      mImageView.setDrawingCacheEnabled(false);
      ivW = srcBitmap.getWidth();
      ivH = srcBitmap.getHeight();

      if (pX > ivX && pX < ivX + ivW) {
        cutLeftWidth = pX - ivX;
      } else {
        return false;
      }
      //将裁剪后的Bitmap放入MoveComponent中并初始化
      Bitmap leftBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, cutLeftWidth, ivH);
      MoveComponent mcL = new MoveComponent(leftBitmap, getContext(), (ViewGroup)ivHolder);
      Bitmap rightBitmap = Bitmap.createBitmap(srcBitmap, cutLeftWidth, 0, ivW - cutLeftWidth, ivH);
      MoveComponent mcR = new MoveComponent(rightBitmap, getContext(), (ViewGroup)ivHolder);
      ivY = ivY + (int)MoveComponent.getNotifyBarHeight();
      mcL.setLocation(ivX - 1,ivY);
      mcR.setLocation(ivX + cutLeftWidth + 1,ivY);
      //回收原图并隐藏ImageView
      if (!srcBitmap.equals(leftBitmap) && !srcBitmap.equals(rightBitmap)
          && !srcBitmap.isRecycled()) {
        GlideBitmapPool.putBitmap(srcBitmap);
      }
      mImageView.setVisibility(View.GONE);
      return true;
    }

    /*****合成图像*****/
    //合成当前ViewGroup中全部MoveComponent
    private void combineAll() {
      MoveComponent mc1, mc2;
      mc2 = MoveComponent.getLastMc();
      while (mc2 != null && mc2.getLeftMc() != null) {
        mc1 = mc2.getLeftMc();
        //通过循环调用combine2Mc实现
        mc2 = combine2Mc(mc1, mc2);
      }
      if (mc2 != null) {
        mBitmap = mc2.getBitmap();
        mImageView.setImageBitmap(mBitmap);
        mImageView.setVisibility(View.VISIBLE);
        mc2.remove();
      }
    }
    //拼合两张MoveComponent
    private MoveComponent combine2Mc(MoveComponent mc1, MoveComponent mc2) {
      float aX, aY, bX, bY, newX, newY;
      int aBmWidth, aBmHeight, bBmWidth, bBmHeight, newBmWidth, newBmHeight, p;
      Bitmap background, foreground;
      p = MoveComponent.getProportion();
      //计算上下层叠顺序
      if (mc1.getMyOrder() > mc2.getMyOrder()) {
        aX = mc2.getX();
        bX = mc1.getX();
        aY = mc2.getY();
        bY = mc1.getY();
        background = mc2.getBitmap();
        foreground = mc1.getBitmap();
      } else {
        aX = mc1.getX();
        bX = mc2.getX();
        aY = mc1.getY();
        bY = mc2.getY();
        background = mc1.getBitmap();
        foreground = mc2.getBitmap();
      }
      if (background == null) {
        return null;
      }

      newX = (float)min(aX, bX);
      newY = (float)min(aY, bY);
      aBmWidth = background.getWidth();
      aBmHeight = background.getHeight();
      bBmWidth = foreground.getWidth();
      bBmHeight = foreground.getHeight();
      newBmWidth = (int)(max(aX * p + aBmWidth , bX * p + bBmWidth) - newX * p);
      newBmHeight = (int)(max(aY * p + aBmHeight , bY * p + bBmHeight) - newY * p);

      Bitmap newMap = Bitmap.createBitmap(newBmWidth, newBmHeight, Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(newMap);
      canvas.drawBitmap(background, (aX - newX) * p, (aY - newY) * p, null);
      canvas.drawBitmap(foreground, (bX - newX) * p,(bY - newY) * p, null);
      canvas.save();
      canvas.restore();

      MoveComponent newMc = new MoveComponent(newMap, getContext(), ivHolder);
      int newOrder = max(mc1.getMyOrder(),mc2.getMyOrder());
      newMc.setLocation(newX, newY, newOrder);
      MoveComponent.remove2();
      return newMc;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
        @NonNull String[] permissions, @NonNull int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      EasyPermissions.onRequestPermissionsResult(requestCode, permissions,
          grantResults, this);
    }

    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    //用户未同意使用权限
    @Override
    public void onPermissionsDenied(int requestCode,
        @NonNull List<String> perms) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      String photoPath;
      Log.i("uploadFile", "wei chu shi hua");
      if (requestCode == 1 && resultCode == RESULT_OK) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          photoPath = String.valueOf(savePath);
          } else {
          photoPath = uri.getEncodedPath();
        }
        Glide.with(getActivity()).load(photoPath).into(mImageView);
        Log.i("uploadFile", photoPath); } else if (requestCode == 2 && resultCode == RESULT_OK) {
        photoPath = GetPhotoFromGallery.getRealPathFromUri(getContext(), data.getData());
        Glide.with(getActivity()).load(photoPath).into(mImageView);
        Log.i("uploadFile", photoPath);
      }
      super.onActivityResult(requestCode, resultCode, data);
    }

    private void updatePath(String tag){
      imageCount++;
      savePath = new File(Environment.getExternalStorageDirectory().getPath()
          + "/" + System.currentTimeMillis() + tag  + Integer.toString(imageCount)+ ".jpg");
    }

    @Override
    public EditPresenter createPresenter() {
      return new EditPresenter();
    }

    @Override
    public EditView createView() {
      return this;
    }

    @Override
    public void onSaveResult(boolean success, String result) {
      Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
    }
}
