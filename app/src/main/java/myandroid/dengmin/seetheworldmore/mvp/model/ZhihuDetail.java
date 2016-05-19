package myandroid.dengmin.seetheworldmore.mvp.model;

import myandroid.dengmin.seetheworldmore.mvp.other.NewsDetail;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dmin on 2016/5/14.
 * Zhihu news detail class
 */
public class ZhihuDetail extends RealmObject implements NewsDetail{

    /**
     * body : <div class="main-wrap content-wrap">
     <p>致敬是对某个桥段，某几个镜头，某个造型，某段对话高度复制，属于表达导演对自己偶像的敬仰，一般只有资深影迷才会发现。</p>
     * blah blah
     </div>
     * image_source : 《一步之遥》
     * title : 致敬、恶搞、借鉴、模仿、抄袭，到底怎么区分？
     * image : http://pic1.zhimg.com/930cf6f414db290556cd068235ff8f1c.jpg
     * share_url : http://daily.zhihu.com/story/7815067
     * js : []
     * ga_prefix : 013010
     * type : 0
     * id : 7815067
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=77778"]
     */

    private String body;
    private String image_soure;
    private String title;
    private String image;
    private String share_url;
    @PrimaryKey
    private int id;
    private RealmList<RealmString> css;

    public void setBody(String body) {
        this.body = body;
    }

    public void setCss(RealmList<RealmString> css) {
        this.css = css;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImage_soure(String image_soure) {
        this.image_soure = image_soure;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public RealmList<RealmString> getCss() {
        return css;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getImage_soure() {
        return image_soure;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getTitle() {
        return title;
    }
}
