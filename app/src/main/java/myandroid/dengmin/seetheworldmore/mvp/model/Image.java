package myandroid.dengmin.seetheworldmore.mvp.model;

import android.content.Context;
import myandroid.dengmin.seetheworldmore.mvp.other.Data;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dmin on 2016/5/12.
 */
public class Image extends RealmObject implements Data {

    private int id;
    private int type;//GAnk or DB
    private String publishedAt;

    public Image(){
    }

    public Image(String url){
        this.url = url;
    }

    public Image(String url, int type) {
        this.url = url;
        this.type = type;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @PrimaryKey
    private String url;
    private int width;
    private int height;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {

        return height;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public static Image getFixedImage(Context context,String url,int type) throws ExecutionException, InterruptedException {
        Image image = new Image(url,type);
        Bitmap bitmap = Glide.with(context)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .get();
        image.setWidth(bitmap.getWidth());
        image.setHeight(bitmap.getHeight());
        return image;
    }
}
