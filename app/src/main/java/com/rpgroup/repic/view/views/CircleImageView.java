package com.rpgroup.repic.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
  public CircleImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  public CircleImageView(Context context) {
    super(context);
  }
  public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
  }
  @Override
  protected void onDraw(Canvas canvas) {
    Path clipPath = new Path();
    int w = this.getWidth();
    int h = this.getHeight();
    clipPath.addRoundRect(new RectF(0, 0, w, h), 30.0f, 30.0f, Path.Direction.CW);
    canvas.clipPath(clipPath);
    super.onDraw(canvas);
  }
}
