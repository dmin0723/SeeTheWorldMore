package myandroid.dengmin.seetheworldmore.net;

import myandroid.dengmin.seetheworldmore.mvp.model.FreshDetailJson;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshJson;
import myandroid.dengmin.seetheworldmore.mvp.model.RealmString;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuDetail;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuJson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by dmin on 2016/5/13.
 * Json parser util
 */
public class Json {

    public Json(){
    }

    public static Type token = new TypeToken<RealmList<RealmString>>(){}.getType();

    public static Gson mGson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaredClass().equals(RealmObject.class);//需要修改
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    })
            .registerTypeAdapter(token, new TypeAdapter<RealmList<RealmString>>() {
                @Override
                public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
                    //Ignore
                }

                @Override
                public RealmList<RealmString> read(JsonReader in) throws IOException {
                    //以下需要修改
                    RealmList<RealmString> list = new RealmList<RealmString>();
                    in.beginArray();
                    while(in.hasNext()){
                        list.add(new RealmString(in.nextString()));
                    }
                    in.endArray();
                    return list;
                }
            }).create();

    public static ZhihuJson parseZhihuNews(String latest){
        return mGson.fromJson(latest,ZhihuJson.class);
    }

    public static ZhihuDetail parseZhihuDetail(String detail){
        return  mGson.fromJson(detail,ZhihuDetail.class);
    }

    public static FreshJson parseFreshNews(String fresh){
        return mGson.fromJson(fresh,FreshJson.class);
    }

    public static FreshDetailJson parseFreshDetail(String detail){
        return mGson.fromJson(detail,FreshDetailJson.class);
    }

    public static <Data> Data parseNews(String response,Class<Data> clz){
        return mGson.fromJson(response,clz);
    }
}
