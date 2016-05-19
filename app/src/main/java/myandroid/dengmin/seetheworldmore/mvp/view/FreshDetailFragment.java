package myandroid.dengmin.seetheworldmore.mvp.view;

import android.annotation.TargetApi;
import myandroid.dengmin.seetheworldmore.R;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailPresenter;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailView;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshDetailJson;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshPost;
import myandroid.dengmin.seetheworldmore.mvp.presenter.FreshDetailPresenter;
import myandroid.dengmin.seetheworldmore.net.DB;
import myandroid.dengmin.seetheworldmore.ui.BaseActivity;
import myandroid.dengmin.seetheworldmore.ui.BaseFragment;
import myandroid.dengmin.seetheworldmore.utils.Constants;
import myandroid.dengmin.seetheworldmore.utils.Share;
import myandroid.dengmin.seetheworldmore.utils.UI;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

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
    FrameLayout webContainer;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public List<FreshPost> freshPosts;
    private WebView webView;
    private FreshPost freshPost;
    private NewsDetailPresenter<FreshPost> presenter;
    private ShareActionProvider mShareActionProvider;
    private int position;
    private BaseActivity context;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (BaseActivity) getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_fresh_detail;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        if(getArguments() != null){
            position = getArguments().getInt(Constants.POSITION);
            freshPosts = DB.findAllDateSorted(context.mRealm, FreshPost.class);
            freshPost = freshPosts.get(position);
        }
        presenter.loadNewsDetail(freshPost);
        toolbar.setTitle(freshPost.getTitle());
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);//出现问题，使用错的import
    }

    @Override
    protected void AlwaysInit() {
        super.AlwaysInit();
        initWebView();
    }

    private void initWebView() {
        webView = new WebView(getActivity());
        webContainer.addView(webView);
        webView.setVisibility(View.INVISIBLE);

        WebSettings setting = webView.getSettings();
        setting.setTextZoom(110);
        setting.setJavaScriptEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        setting.setAppCacheEnabled(true);
        setting.setDomStorageEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(final WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress == 100){
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                            hideProgress();
                        }
                    },200);
                }
            }
        });
    }

    @Override
    protected void initViews() {
        presenter = new FreshDetailPresenter(this,context);
    }


    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetail(FreshDetailJson detailNews) {
        webView.loadDataWithBaseURL("x-data://base",
                detailNews.getPost().getContent(),"text/html","UTF-8",null);
    }

    @Override
    public void hideProgress() {
        if(progress != null){
            progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoadFailed(String msg) {
        if(rootView != null){
            UI.showSnack(rootView,R.string.load_fail);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.share_menu,menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareIntetn();
    }

    private void setShareIntetn() {
        if(mShareActionProvider != null){
            mShareActionProvider.setShareIntent(
                    Share.getShareIntent(freshPost.getUrl()));
        }
    }
}
