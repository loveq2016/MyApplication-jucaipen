package com.example.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class GraphicsView extends View {
	// 声明画笔
	Paint paint = null;

	public GraphicsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// paint = new Paint();
	}

	public GraphicsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new Paint();
	}

	public GraphicsView(Context context) {
		super(context);
		initView();

	}

	private void initView() {
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);
	}

	public void draw() {
		paint.setColor(Color.GREEN);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(80, 80, 360, paint);

	}

	/*
	 * @Override protected void onDraw(Canvas canvas) { // TODO Auto-generated
	 * method stub super.onDraw(canvas);
	 *
	 * //设置画布背景为白色 canvas.drawColor(Color.WHITE); //设置画笔为红色
	 * paint.setColor(Color.RED); //使用当前画笔绘制一个左上角坐标为80,20，右下角坐标为360,180的矩形
	 * canvas.drawRect(80,20,360,180, paint);
	 *
	 * //设置画笔为绿色 paint.setColor(Color.GREEN); //使用当前画笔绘制一个圆心坐标为220,100，半径为60的圆
	 * canvas.drawCircle(220,100, 60, paint); Log.i("111", "ppp");
	 * 
	 * 
	 * 
	 * }
	 */

	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

}
