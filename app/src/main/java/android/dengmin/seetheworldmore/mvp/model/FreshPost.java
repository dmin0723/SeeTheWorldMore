package android.dengmin.seetheworldmore.mvp.model;

import android.content.IntentFilter;
import android.dengmin.seetheworldmore.mvp.other.NewsItem;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by dmin on 2016/5/13.
 */
public class FreshPost extends RealmObject implements NewsItem {
    @Ignore
    private IntentFilter.AuthorityEntry author;
    private int comment_count;
    private FreshCustomFields custom_fields;

    public FreshCustomFields getCustom_fields(){
        return custom_fields;
    }
}
