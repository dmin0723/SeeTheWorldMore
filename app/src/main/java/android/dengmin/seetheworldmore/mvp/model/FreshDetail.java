package android.dengmin.seetheworldmore.mvp.model;

import io.realm.RealmObject;

/**
 * Created by dmin on 2016/5/13.
 * Fresh detail post.
 */
public class FreshDetail extends RealmObject {

    private int id;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }
}
