package android.dengmin.seetheworldmore.mvp.other;

import android.content.Context;
import android.dengmin.seetheworldmore.mvp.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by dmin on 2016/5/13.
 * A helper of Image model transactions.
 */
public class ImageHelper {

    private int type;
    private Context context;

    public ImageHelper(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public List<Image> saveImages(String[] urls,String[] publishAts){
        List<Image> images = new ArrayList<>();
        for(int i = 0; i < urls.length;i++){
            try {
                Image image = Image.getFixedImage(context,urls[i],type);
                if(publishAts != null){
                    image.setPublishedAt(publishAts[i]);
                }
                images.add(image);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return images;
    }

    public List<Image> saveImages(String[] urls){
        return saveImages(urls,null);
    }
}
