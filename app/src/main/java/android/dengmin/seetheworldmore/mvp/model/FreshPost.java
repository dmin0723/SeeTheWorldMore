package android.dengmin.seetheworldmore.mvp.model;

import android.dengmin.seetheworldmore.mvp.other.NewsItem;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dmin on 2016/5/13.
 * fresh things item in list
 */
public class FreshPost extends RealmObject implements NewsItem {
    private int id;
    private String url;
    private String title;

    @PrimaryKey
    private String date;

    /**
     * TagsEntity
     *
     * id : 489 //
     * slug : %e5%86%b7%e6%96%b0%e9%97%bb
     * title : 冷新闻 //
     * description :
     * post_count : 3769
     */

    @Ignore
    private List<TagsEntity> tags;

    /**
     * AuthorEntity
     *
     * id : 593 //
     * slug : banana
     * name : 一只咸鱼 //
     * first_name :
     * last_name :
     * nickname : 一只咸鱼
     * url : //
     * description :
     */

    @Ignore
    private AuthorEntity author;

    private int comment_count;
    private FreshCustomFields custom_fields;

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setCustom_fields(FreshCustomFields custom_fields) {
        this.custom_fields = custom_fields;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public int getComment_count() {
        return comment_count;
    }

    public FreshCustomFields getCustom_fields() {
        return custom_fields;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public static class AuthorEntity implements Serializable{
        /**
         * AuthorEntity
         *
         * id : 593 //
         * slug : banana
         * name : 一只咸鱼 //
         * first_name :
         * last_name :
         * nickname : 一只咸鱼
         * url : //
         * description :
         */
        private int id;
        private String slug;
        private String name;
        private String first_name;
        private String last_name;
        private String nick_name;
        private String url;
        private String description;

        public void setDescription(String description) {
            this.description = description;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public String getFirst_name() {
            return first_name;
        }

        public int getId() {
            return id;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getName() {
            return name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public String getSlug() {
            return slug;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class TagsEntity implements Serializable{
        private int id;
        private String slug;
        private String title;
        private String description;
        private int post_count;

        public void setDescription(String description) {
            this.description = description;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPost_count(int post_count) {
            this.post_count = post_count;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }

        public int getPost_count() {
            return post_count;
        }

        public String getSlug() {
            return slug;
        }

        public String getTitle() {
            return title;
        }
    }
}
