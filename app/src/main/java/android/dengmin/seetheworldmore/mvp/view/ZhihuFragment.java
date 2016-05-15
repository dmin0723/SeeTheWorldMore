package android.dengmin.seetheworldmore.mvp.view;

import android.content.Intent;
import android.dengmin.seetheworldmore.MainActivity;
import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.mvp.interf.NewsPresenter;
import android.dengmin.seetheworldmore.mvp.interf.NewsView;
import android.dengmin.seetheworldmore.mvp.interf.OnListFragmentInteract;
import android.dengmin.seetheworldmore.mvp.model.ZhihuJson;
import android.dengmin.seetheworldmore.mvp.other.ZhihuListAdapter;
import android.dengmin.seetheworldmore.mvp.presenter.ZhihuDataPresenter;
import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.dengmin.seetheworldmore.utils.Constants;
import android.dengmin.seetheworldmore.utils.SharedPreferencesUtil;
import android.dengmin.seetheworldmore.utils.UI;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by dmin on 2016/5/15.
 */
public class ZhihuFragment extends RecyclerFragment implements NewsView<ZhihuJson>,SwipeRefreshLayout.OnRefreshListener,OnListFragmentInteract{

    private static final int PRELOAD_COUNT = 1;
    private static final String TAG = "test";

    private NewsPresenter presenter;
    private ZhihuListAdapter adapter;
    private ConvenientBanner banner;
    private LinearLayoutManager layoutManager;
    private BaseActivity mActivity;

    @Override
    public void onDestroyView() {
        OkHttpUtils.getInstance().cancelTag(mActivity);
        SharedPreferencesUtil.save(type + Constants.POSITION,firstPosition);
        if(banner != null ){
            banner.stopTurning();
            Log.i(TAG,"onDestroyView: stop banner");
        }
        super.onDestroyView();
    }

    @Override
    protected void AlwaysInit() {
        super.AlwaysInit();
    }

    @Override
    protected void initViews() {
        super.initViews();
        type = TabFragment.TYPE_ZHIHU;
        mActivity = (BaseActivity) getActivity();
        layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ZhihuListAdapter(this,mActivity);

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

    @Override
    protected void initData() {
        presenter = new ZhihuDataPresenter(this,(BaseActivity) getActivity());
        initBanner();
        onRefresh();
    }

    private void onListScrolled() {
        initBanner();
        firstPosition = layoutManager.findFirstVisibleItemPosition();
        lastPosition = layoutManager.findLastVisibleItemPosition();
        if(lastPosition + PRELOAD_COUNT == adapter.getItemCount()){
            presenter.loadBefore();
        }
    }

    private void initBanner() {
        if(null == banner){
            if(recyclerView.getChildCount() != 0 && layoutManager.findFirstVisibleItemPosition() == 0){
                banner = (ConvenientBanner) layoutManager.findViewByPosition(0);
                banner.setScrollDuration(1000);
                banner.startTurning(5000);
            }
        }
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void onRefresh() {
        presenter.loadNews();
    }

    @Override
    public void addNews(ZhihuJson news) {
        adapter.addNews(news);
    }

    @Override
    public void hidePregress() {
        showProgress(false);
    }

    @Override
    public void loadFailed(String msg) {
        if (isAlive()){
            UI.showSnack(((MainActivity) getActivity()).getDrawerLayout(), R.string.load_fail);
        }
    }

    @Override
    public void OnListFragmentInteraction(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof ZhihuListAdapter.ViewHolder){
            ZhihuListAdapter.ViewHolder holder  = (ZhihuListAdapter.ViewHolder)viewHolder;
            Intent intent = new Intent(getActivity(),ZhihuDetailActivity.class);
            intent.putExtra(Constants.ID,holder.zhihuStory.getId());
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    holder.mImage,getString(R.string.shared_img));
            ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());
            holder.mTitle.setTextColor(ZhihuListAdapter.textGrey);
        }
    }
}
