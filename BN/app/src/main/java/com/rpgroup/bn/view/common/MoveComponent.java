package com.rpgroup.bn.view.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

//可拖动可拼合图片类
public class MoveComponent {

  private final int[] lctView = new int[2];
  private int myOrder;

  private float x, y, rX, rY, tchX, tchY;
  private final ImageView img;
  private final Bitmap bitmap;
  private MoveComponent leftMc, rightMc;

  private static float notifyBarHeight;
  private static int lastOrder = 0;//最后一次移动组件的Z轴值，从0开始
  private static final int proportion = 1;//图片缩放比例，用于计算初始化及拼合时的尺寸，目前写死
  private static MoveComponent lastMc = null;//用链表存储当前ViewGroup中所有MoveComponent
  private static ViewGroup vg;

  public MoveComponent(Bitmap bm, Context mac, ViewGroup vg) {
    bitmap = bm;
    int w = bitmap.getWidth() / proportion;
    int h = bitmap.getHeight() / proportion;

    img = new ImageView(mac);
    img.setLayoutParams(new LayoutParams(w,h));
    img.setImageBitmap(bitmap);
    this.vg = vg;
    this.vg.addView(img);

    img.getLocationOnScreen(lctView);
    x = (float)lctView[0];
    y = (float)lctView[1];

    tchX = 0;
    tchY = 0;
    rX = 0;
    rY = 0;
    leftMc = null;
    rightMc = null;
    notifyBarHeight = y - img.getY();//适配屏幕坐标

    lastOrder++;
    raise(lastOrder);
    img.setOnTouchListener(new MoveTouchListener());
  }

  public static int getProportion() {
    return proportion;
  }

  public static void setLastMc(MoveComponent lastMc) {
    MoveComponent.lastMc = lastMc;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public Bitmap getBitmap() {
    return bitmap;
  }

  public int getMyOrder() {
    return myOrder;
  }

  public static float getNotifyBarHeight() {
    return notifyBarHeight;
  }

  //初始化时设置三轴属性值
  public void setLocation(float x, float y, int z) {
    this.x = x;
    this.y = y;
    img.setX(x);
    img.setY(y - notifyBarHeight);
    raise(z);
  }
  //重载使z轴默认为最上层
  public void setLocation(float x, float y) {
    lastOrder++;
    setLocation(x,y,lastOrder);
  }

  //获取链表尾，即最后移动的MoveComponent，也是拼合操作的操作对象
  public static MoveComponent getLastMc() {
    return lastMc;
  }

  public MoveComponent getLeftMc() {
    return leftMc;
  }

  //从链表摘除
  public  void remove() {
    vg.removeView(img);
    if (this == lastMc) {
      lastMc = null;
    } else if (leftMc != null && rightMc != null) {
      leftMc.rightMc = rightMc;
      rightMc.leftMc = leftMc;
    }
  }

  //摘除最后两个，用于拼合操作后
  public static void remove2() {
    if (lastMc != null && lastMc.leftMc != null && lastMc.leftMc.leftMc != null) {
      vg.removeView(lastMc.leftMc.leftMc.img);
      vg.removeView(lastMc.leftMc.img);
      lastMc.leftMc = lastMc.leftMc.leftMc.leftMc;
      if (lastMc.leftMc != null) {
        lastMc.leftMc.rightMc = lastMc;
      }
    }
  }

  //重设z轴值，并调节其在链表中的位置
  private void raise(int z) {
    myOrder = z;
    img.setZ(myOrder);

    if (lastMc == this) {

    } else if (leftMc == null && rightMc == null) {
      leftMc = lastMc;
      if (lastMc != null) {
        lastMc.rightMc = this;
      }
      lastMc = this;

    } else {
      rightMc.leftMc = leftMc;
      if (leftMc != null) {
        leftMc.rightMc = rightMc;
      }
      rightMc = null;
      leftMc = lastMc;
      lastMc.rightMc = this;
      lastMc = this;

    }
  }


  private class MoveTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
      switch (event.getAction()) {

        case MotionEvent.ACTION_DOWN:
          img.getLocationOnScreen(lctView);
          x = (float) lctView[0];
          y = (float) lctView[1];
          tchX = event.getRawX();
          tchY = event.getRawY();
          rX = x - tchX;
          rY = y - tchY;
          notifyBarHeight = y - img.getY();

          lastOrder++;
          raise(lastOrder);
          //通过点击时重设组件的Z轴坐标，并更新lastOrder
          //实现最后点击的在最上层
          break;

        case MotionEvent.ACTION_MOVE:
          tchX = event.getRawX();
          tchY = event.getRawY();
          img.setX(rX + tchX);
          img.setY(rY + tchY - notifyBarHeight);

          img.getLocationOnScreen(lctView);
          x = (float) lctView[0];
          y = (float) lctView[1];

          break;

        case MotionEvent.ACTION_UP:
          break;

        case MotionEvent.ACTION_CANCEL:
          break;

        default:
          break;
      }
      return true;
    }
  }
}
