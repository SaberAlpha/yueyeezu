package com.zxing.encoding;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.administrator.suvsproject.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;
/**
 * @author Ryan Tang
 *
 */
public final class EncodingHandler {
	private static final int BLACK = 0xff000000;
	
	public static Bitmap createQRCode(String str, int widthAndHeight, Context context) throws WriterException {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = BLACK;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//		bitmap=addLogo(bitmap,context);
		return bitmap;
	}

	private static Bitmap addLogo(Bitmap bitmap, Context context) {

		int halfwidth = bitmap.getWidth() / 6 / 2;
		Canvas canvas=new Canvas(bitmap);
		Bitmap logo= BitmapFactory.decodeResource(context.getResources(), R.drawable.logoinlogo);

		Rect src=new Rect();
		src.left=0;
		src.top=0;
		src.bottom=logo.getHeight();
		src.right=logo.getWidth();

		Rect dst=new Rect();
		dst.top=bitmap.getHeight()/2-halfwidth;
		dst.bottom=bitmap.getHeight()/2+halfwidth;
		dst.left=bitmap.getWidth()/2-halfwidth;
		dst.right=bitmap.getWidth()/2+halfwidth;

		canvas.drawBitmap(logo,src,dst,null);


		return bitmap;
	}
}
