package com.example.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.ImageView;

public class MyImageview extends ImageView {
	Paint paint = null;
	private Bitmap bitmap;
	private Matrix matrix;

	public MyImageview(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas = new Canvas(bitmap);

		float cx = getWidth() / 2;
		float cy = getHeight() / 2;
		float radius = Math.min(getWidth(), getHeight()) / 2;
		Paint borderPaint = new Paint();
		borderPaint.setAntiAlias(true);
		borderPaint.setColor(Color.GREEN);

		canvas.drawCircle(cx, cy, radius, borderPaint);

	}
}