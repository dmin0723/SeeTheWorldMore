package android.dengmin.seetheworldmore.mvp.view;

import android.dengmin.seetheworldmore.mvp.interf.OnLoadDataListener;
import android.dengmin.seetheworldmore.utils.Constants;
import android.os.Bundle;

/**
 * Created by dmin on 2016/5/15.
 */
public class PictureFragment extends RecyclerFragment implements OnLoadDataListener {

    public static final int TYPE_GANK = 0;
    public static final int TYPE_DB_BREAST = 1;
    public static final int TYPE_DB_BUTT = 2;
    public static final int TYPE_DB_SILK = 3;
    public static final int TYPE_DB_LEG = 4;
    public static final int TYPE_DB_RANK = 5;

    public static PictureFragment newInstance(int type){
        Bundle args = new Bundle();
        args.putInt(Constants.TYPE,type);
        PictureFragment fragment = new PictureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onRefresh() {

    }
}
