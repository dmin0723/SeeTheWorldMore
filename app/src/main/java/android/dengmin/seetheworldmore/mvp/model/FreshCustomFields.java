package android.dengmin.seetheworldmore.mvp.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by dmin on 2016/5/13.
 */
public class FreshCustomFields extends RealmObject {
    //RealmString support List<String>

    private RealmList<RealmString> thumb_c;

    public RealmList<RealmString> getThumb_c() {
        return thumb_c;
    }

    public void setThumb_c(RealmList<RealmString> thumb_c) {
        this.thumb_c = thumb_c;
    }
}
