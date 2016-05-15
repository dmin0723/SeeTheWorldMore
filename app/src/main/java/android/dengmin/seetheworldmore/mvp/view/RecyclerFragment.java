package android.dengmin.seetheworldmore.mvp.view;

import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.ui.BaseFragment;
import android.dengmin.seetheworldmore.utils.Constants;
import android.dengmin.seetheworldmore.utils.SharedPreferencesUtil;
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

    @Bind(R.id.swipe_refresh)
   SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.list)
    RecyclerView recyclerView;

    boolean isFirst = true;//whether id first time to entern fragment
    int type;              //type of recyclerView's content
    int lastPosition;      //last visible position
    int firstPosition;     //first visible position

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_recycler;
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
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary,
                R.color.colorPrimaryDark,R.color.colorAccent);
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
