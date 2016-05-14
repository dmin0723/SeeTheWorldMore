package android.dengmin.seetheworldmore.net;

import android.dengmin.seetheworldmore.utils.Constants;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * Created by dmin on 2016/5/13.
 */
public class DB {

    public static void saveORUpdate(Realm realm,RealmObject realmObject){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObject);
        realm.commitTransaction();
    }

    public static <T extends RealmObject> void saveList(Realm realm, List<T> realmObjects){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObjects);
        realm.commitTransaction();
    }

    public static void save(Realm realm,RealmObject realmObject){
        realm.beginTransaction();
        realm.copyToRealm(realmObject);
        realm.commitTransaction();
    }

    public static <T extends RealmObject> T getById(Realm realm,int id,Class<T> realmObjectClass){
        return realm.where(realmObjectClass).equalTo("id",id).findFirst();

    }

    public static <T extends RealmObject> RealmResults<T> findAll(Realm realm, Class<T> realmObjectClass){
        return realm.where(realmObjectClass).findAll();
    }

    public static <T extends RealmObject>RealmResults<T> findAllDateSorted(Realm realm,Class<T> realmObjectClass){
        RealmResults<T> results = findAll(realm,realmObjectClass);
        results.sort(Constants.DATE, Sort.DESCENDING);
        return results;
    }
}
