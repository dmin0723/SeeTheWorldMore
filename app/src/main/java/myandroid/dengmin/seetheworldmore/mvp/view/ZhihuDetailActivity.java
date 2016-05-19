package myandroid.dengmin.seetheworldmore.mvp.view;

import android.annotation.SuppressLint;
import myandroid.dengmin.seetheworldmore.R;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailPresenter;
import myandroid.dengmin.seetheworldmore.mvp.interf.NewsDetailView;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuDetail;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuStory;
import myandroid.dengmin.seetheworldmore.mvp.model.ZhihuTop;
import myandroid.dengmin.seetheworldmore.mvp.presenter.ZhihuDetailPresenter;
import myandroid.dengmin.seetheworldmore.net.DB;
import myandroid.dengmin.seetheworldmore.ui.BaseActivity;
import myandroid.dengmin.seetheworldmore.utils.Constants;
import myandroid.dengmin.seetheworldmore.utils.Imager;
import myandroid.dengmin.seetheworldmore.utils.Share;
import myandroid.dengmin.seetheworldmore.utils.UI;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.Bind;

/**
 * Created by dmin on 2016/5/13.
 */
public class ZhihuDetailActivity extends BaseActivity implements NewsDetailView<ZhihuDetail>{

    @Bind(R.id.detail_image)
    ImageView detailImage;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.web_container)
    FrameLayout webContainer;
    private WebView webView;
    private ZhihuDetail zhihuDetail;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        int id = getIntent().getIntExtra(Constants.ID,0);
        ZhihuStory story = DB.getById(mRealm,id, ZhihuStory.class);
        zhihuDetail = DB.getById(mRealm,id,ZhihuDetail.class);
        if(story == null){
            //can't find zhihuItem,so this id is passed by zhihutop
            toolbarLayout.setTitle(DB.getById(mRealm,id, ZhihuTop.class).getTitle());
        }else{
            toolbarLayout.setTitle(story.getTitle());
        }

        NewsDetailPresenter<ZhihuStory> presenter = new ZhihuDetailPresenter(this,this);
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView = new WebView(this);
        webContainer.addView(webView);
        webView.setVisibility(View.INVISIBLE);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);

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
                    },300);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        webView.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetail(ZhihuDetail detailNews) {
        zhihuDetail = detailNews;
        Imager.load(this,detailNews.getImage(),detailImage);
        //add css style to webView
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + detailNews.getBody() + "</<body></html>";
        html = html.replace("<div class=\"img-place-holder\">","");
        webView.loadDataWithBaseURL("x-data://base",html,"text/html","UTF-8",null);
        initFAB();
    }

    private void initFAB() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share.shareText(ZhihuDetailActivity.this,zhihuDetail.getShare_url());
            }
        });
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showLoadFailed(String msg) {
        UI.showSnack(webContainer,R.string.load_fail);
    }
}
