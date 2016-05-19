package myandroid.dengmin.seetheworldmore.mvp.model;

import io.realm.RealmObject;

/**
 * Created by dmin on 2016/5/13.
 * Zhihu news item in top banner
 */
public class ZhihuTop extends RealmObject {
    //image id title
    private String image;
    private int id;
    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
