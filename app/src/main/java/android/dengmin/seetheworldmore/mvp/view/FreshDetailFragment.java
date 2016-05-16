package android.dengmin.seetheworldmore.mvp.view;

import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.mvp.interf.NewsDetailPresenter;
import android.dengmin.seetheworldmore.mvp.interf.NewsDetailView;
import android.dengmin.seetheworldmore.mvp.model.FreshDetailJson;
import android.dengmin.seetheworldmore.mvp.model.FreshPost;
import android.dengmin.seetheworldmore.ui.BaseFragment;
import android.dengmin.seetheworldmore.utils.Constants;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import java.util.List;

import butterknife.Bind;

/**
 * Created by dmin on 2016/5/16.
 * A simple {@link Fragment} subclass.
 * Use the {@link FreshDetailFragment#newInstance} factory method to
 * createAPI an instance of this fragment;
 */
public class FreshDetailFragment extends BaseFragment implements NewsDetailView<FreshDetailJson>{

    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.web_container)
    Fragment webContainer;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public List<FreshPost> freshPosts;
    private WebView webView;
    private FreshPost freshPost;
    private NewsDetailPresenter<FreshPost> presenter;


    public  FreshDetailFragment(){
    }

   public static FreshDetailFragment newInstance(int position){
       FreshDetailFragment fragment = new FreshDetailFragment();
       Bundle args = new Bundle();
       args.putInt(Constants.POSITION,position);
       fragment.setArguments(args);
       return  fragment;
   }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        layoutId = R.layout.fragment_fresh_detail;
    }

    @Override
    protected void initLayoutId() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showDetail(FreshDetailJson detailNews) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailed(String msg) {

    }
}
