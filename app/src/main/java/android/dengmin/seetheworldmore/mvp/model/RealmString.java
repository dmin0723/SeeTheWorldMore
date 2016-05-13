package android.dengmin.seetheworldmore.mvp.model;

import io.realm.RealmObject;

/**
 * Created by dmin on 2016/5/13.
 * To make realmObject support List<String>
 */
public class RealmString extends RealmObject{
    private String realmString;

    public RealmString(){

    }

    public RealmString(String realmString){
        this.realmString = realmString;
    }

    public String getRealmString() {
        return realmString;
    }

    public void setRealmString(String realmString) {
        this.realmString = realmString;
    }
}
