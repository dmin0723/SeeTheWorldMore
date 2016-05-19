package myandroid.dengmin.seetheworldmore.mvp.view;

import android.content.Intent;
import myandroid.dengmin.seetheworldmore.MainActivity;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsPresenter;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsView;
import myandroid.dengmin.seetheworldmore.mvp.interf.OnListFragmentInteract;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshJson;
import myandroid.dengmin.seetheworldmore.mvp.other.FreshListAdapter;
import myandroid.dengmin.seetheworldmore.mvp.other.ZhihuListAdapter;
import myandroid.dengmin.seetheworldmore.mvp.presenter.FreshDataPresenter;
import myandroid.dengmin.seetheworldmore.ui.BaseActivity;
import myandroid.dengmin.seetheworldmore.utils.Constants;
import myandroid.dengmin.seetheworldmore.utils.SharedPreferencesUtil;
import myandroid.dengmin.seetheworldmore.utils.UI;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Created by dmin on 2016/5/15.
 * A simple subclss.
 */
public class FreshFragment extends RecyclerFragment implements
        SwipeRefreshLayout.OnRefreshListener,NewsView<FreshJson>,OnListFragmentInteract {

    private static final int PRELOAD_COUNT = 1;
    private NewsPresenter presenter;
    private FreshListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private BaseActivity mActivity;

    //存储数据
    @Override
    public void onDestroyView() {
        SharedPreferencesUtil.save(type + Constants.POSITION,firstPosition);
        super.onDestroyView();
    }

    @Override
    protected void initViews() {
        super.initViews();
        mActivity = (BaseActivity) getActivity();

        type = TabFragment.TYPE_FRESH;
        layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FreshListAdapter(this,mActivity);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
                 if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    onListScrolled();
                 }
             }
        });
    }

    private void onListScrolled() {
        firstPosition = layoutManager.findFirstVisibleItemPosition();
        lastPosition = layoutManager.findLastVisibleItemPosition();

        if(lastPosition + PRELOAD_COUNT == adapter.getItemCount()){
            presenter.loadBefore();
        }
    }

    @Override
    protected void initData() {
        presenter = new FreshDataPresenter(this,mActivity);
        onRefresh();
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void addNews(FreshJson news) {
        adapter.addNews();
    }

    @Override
    public void hidePregress() {
        showProgress(false);
    }

    @Override
    public void loadFailed(String msg) {
        if(isAlive()){
            UI.showSnack(((MainActivity)getActivity()).
                    getDrawerLayout(), myandroid.dengmin.seetheworldmore.R.string.load_fail);
        }
    }

    @Override
    public void OnListFragmentInteraction(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof FreshListAdapter.ViewHolder){
            FreshListAdapter.ViewHolder holder = (FreshListAdapter.ViewHolder) viewHolder;
            holder.mTitle.setTextColor(ZhihuListAdapter.textGrey);
            Intent intent = new Intent(getActivity(),DetailActivity.class);
            intent.putExtra(Constants.MENU_TYPE,TabFragment.MENU_NEWS);
            intent.putExtra(Constants.POSITION,holder.getAdapterPosition());
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        presenter.loadNews();
    }
}
