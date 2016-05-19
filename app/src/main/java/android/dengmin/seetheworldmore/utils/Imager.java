package android.dengmin.seetheworldmore.utils;

import android.content.Context;
import android.dengmin.seetheworldmore.MyApplication;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by dmin on 2016/5/13.
 * 图片下载的封装 使用了glide加载图片
 */
public class Imager {

    public static void load(Context context, String url, ImageView view){
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);
    }

    //main activity中引用 用来加载navigation的header图片
    public static void load(Context context,int resourceId,ImageView view){
        Glide.with(context)
                .load(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(view);
    }

    public static void load(String url,int animationId,ImageView view){
        Glide.with(MyApplication.context)
                .load(url)
                .animate(animationId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);

    }

    public static void loadWithHighPriority(String url,ImageView view){
        Glide.with(MyApplication.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(view);
    }
}
