package android.dengmin.seetheworldmore.mvp.model;

import android.dengmin.seetheworldmore.mvp.other.NewsItem;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dmin on 2016/5/13.
 * Zhihu news item in list
 */
public class ZhihuStory extends RealmObject implements NewsItem {
    /**
     * images : ["http://pic1.zhimg.com/aef18b16a9a6dcb445d5c235784c25a8.jpg"]
     * type : 0
     * id : 7813824
     * ga_prefix : 012915
     * title : 运气好的话，说不定 3 万年就把木星挪过来
     */
    @PrimaryKey
    private int id;
    private int type;
    private String title;
    private RealmList<RealmString> images;

    public ZhihuStory(){

    }

    public ZhihuStory(int date, int type) {
        //for header we use date as id
        id = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public RealmList<RealmString> getImages() {
        return images;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImages(RealmList<RealmString> images) {
        this.images = images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }
}
