package com.lingshimall.lingshixiaomiao.wo;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class DrawArc {
	// 生成圆角图片
	public static Bitmap GetRoundedCornerBitmap(Bitmap bitmap) {
		try {
			// 将图片的四角圆化
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
					bitmap.getHeight(), Config.ARGB_8888);
			// 得到画布
			Canvas canvas = new Canvas(output);
			// 初始化画笔
			final Paint paint = new Paint();
			// 得到与图像相同大小的区域 由构造的四个值决定区域的位置以及大小
			final Rect rect = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			// 值越大角度越明显
			final float roundPx = 30;
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(Color.BLACK);
			// drawRoundRect的第2,3个参数一样则画的是正圆的一角，如果数值不同则是椭圆的一角
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);
			return output;
		} catch (Exception e) {
			return bitmap;
		}
	}
}
