package android.dengmin.seetheworldmore.mvp.model;

import android.dengmin.seetheworldmore.mvp.other.Data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dmin on 2016/5/13.
 * Zhihu news class which contains date,stories and tops.
 */
public class ZhihuJson extends RealmObject implements Data {

/**
 * date : 20160129
 *
 * many stories
 * stories : [{
 * "images":["http://pic1.zhimg.com/aef18b16a9a6dcb445d5c235784c25a8.jpg"],
 * "type":0,
 * "id":7813824,"
 * ga_prefix":"012915",
 * "title":"运气好的话，说不定 3 万年就把木星挪过来"},]
 *
 * five top stories
 *top_stories : [
 * {"image":"http://pic2.zhimg.com/9c8804770cef7efe1b464eb394f73a19.jpg",
 * "type":0,
 * "id":7782299,
 * "ga_prefix":"012914",
 * "title":"讲真，请不要用眼镜布擦眼镜"},]
 */

    @PrimaryKey
    private String date;
    private RealmList<ZhihuStory> stories;
    private RealmList<ZhihuTop> top_stories;

    public String getDate() {
        return date;
    }

    public RealmList<ZhihuStory> getStories() {
        return stories;
    }

    public RealmList<ZhihuTop> getTop_stories() {
        return top_stories;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(RealmList<ZhihuStory> stories) {
        this.stories = stories;
    }

    public void setTop_stories(RealmList<ZhihuTop> top_stories) {
        this.top_stories = top_stories;
    }
}
