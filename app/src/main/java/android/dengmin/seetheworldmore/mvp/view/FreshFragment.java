package android.dengmin.seetheworldmore.mvp.view;

import android.dengmin.seetheworldmore.mvp.interf.NewsView;
import android.dengmin.seetheworldmore.mvp.interf.OnListFragmentInteract;
import android.dengmin.seetheworldmore.mvp.model.FreshJson;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * Created by dmin on 2016/5/15.
 */
public class FreshFragment extends RecyclerFragment implements SwipeRefreshLayout.OnRefreshListener,NewsView<FreshJson>,OnListFragmentInteract {




    @Override
    protected void initData() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void addNews(FreshJson news) {

    }

    @Override
    public void hidePregress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void OnListFragmentInteraction(RecyclerView.ViewHolder holder) {

    }

    @Override
    public void onRefresh() {

    }
}
