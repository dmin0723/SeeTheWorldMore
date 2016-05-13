package android.dengmin.seetheworldmore.net;

import android.dengmin.seetheworldmore.utils.Constants;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * Created by dmin on 2016/5/13.
 */
public class DB {

    public static <T extends RealmObject> RealmResults<T> findAll(Realm realm, Class<T> realmObjectClass){
        return realm.where(realmObjectClass).findAll();
    }

    public static <T extends RealmObject>RealmResults<T> findAllDateSorted(Realm realm,Class<T> realmObjectClass){
        RealmResults<T> results = findAll(realm,realmObjectClass);
        results.sort(Constants.DATE, Sort.DESCENDING);
        return results;
    }
}
