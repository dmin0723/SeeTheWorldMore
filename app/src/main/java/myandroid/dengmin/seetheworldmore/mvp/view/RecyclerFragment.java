package myandroid.dengmin.seetheworldmore.mvp.view;

import myandroid.dengmin.seetheworldmore.ui.BaseFragment;
import myandroid.dengmin.seetheworldmore.utils.Constants;
import myandroid.dengmin.seetheworldmore.utils.SharedPreferencesUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;

/**
 * Created by dmin on 2016/5/15.
 * All fragments have recyclerView & swipeRefresh must implement this.
 */
public abstract class RecyclerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(myandroid.dengmin.seetheworldmore.R.id.swipe_refresh)
   SwipeRefreshLayout swipeRefresh;
    @Bind(myandroid.dengmin.seetheworldmore.R.id.list)
    RecyclerView recyclerView;

    boolean isFirst = true;//whether id first time to entern fragment
    int type;              //type of recyclerView's content
    int lastPosition;      //last visible position
    int firstPosition;     //first visible position

    @Override
    protected void initLayoutId() {
        layoutId = myandroid.dengmin.seetheworldmore.R.layout.fragment_recycler;
    }

    //恢复原来的数据
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState == null){
            //restoring position when reentering fragment
            lastPosition = SharedPreferencesUtil.getInt(type + Constants.POSITION);
            if(lastPosition > 0){
                recyclerView.scrollToPosition(lastPosition);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //储存数据
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferencesUtil.save(type + Constants.POSITION,firstPosition);
    }

    @Override
    protected void initViews() {
        recyclerView.setHasFixedSize(true);
        swipeRefresh.setColorSchemeColors(myandroid.dengmin.seetheworldmore.R.color.colorPrimary,
                myandroid.dengmin.seetheworldmore.R.color.colorPrimaryDark, myandroid.dengmin.seetheworldmore.R.color.colorAccent);
    }

    public void showProgress(final boolean refreshState){
        if(swipeRefresh != null){
            swipeRefresh.setRefreshing(refreshState);
        }
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }
}
