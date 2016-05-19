package myandroid.dengmin.seetheworldmore.utils;

import myandroid.dengmin.seetheworldmore.MyApplication;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;

/**
 * Created by dmin on 2016/5/12.
 * Bitmap 转换的工具类
 */
public class BitmapUtil {

    public static Bitmap drawableToBitmap(Drawable drawable){
        //取drawable的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        //取drawable的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE
                ?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;

        //建立对应的bitmap
        Bitmap bitmap = Bitmap.createBitmap(w,h,config);

        //建立对应bitmap的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,w,h);

        //把drawable内容画到画布中
        drawable.draw(canvas);

        return bitmap;
    }

    public static Uri bitmapToUri(Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path = MediaStore.Images.Media.insertImage(
                MyApplication.context.getContentResolver(),bitmap,"Title",null);
        return Uri.parse(path);
    }

    public static Uri drawableToUri(Drawable drawable){
        return bitmapToUri(drawableToBitmap(drawable));
    }
}
