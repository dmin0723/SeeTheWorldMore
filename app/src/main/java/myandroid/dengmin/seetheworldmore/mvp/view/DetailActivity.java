package myandroid.dengmin.seetheworldmore.mvp.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.realm.RealmChangeListener;
import myandroid.dengmin.seetheworldmore.R;
import myandroid.dengmin.seetheworldmore.mvp.model.FreshPost;
import myandroid.dengmin.seetheworldmore.mvp.model.Image;
import myandroid.dengmin.seetheworldmore.net.DB;
import myandroid.dengmin.seetheworldmore.ui.BaseActivity;
import myandroid.dengmin.seetheworldmore.utils.Constants;
import myandroid.dengmin.seetheworldmore.utils.SharedPreferencesUtil;
import ooo.oxo.library.widget.PullBackLayout;

/**
 * Created by dmin on 2016/5/16.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class DetailActivity extends BaseActivity implements PullBackLayout.Callback,RealmChangeListener{

    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.container)
    Fragment container;

    private DetailPagerAdapter adapter;
    private String menuType;
    private boolean isPicture;
    private int currentPosition;
    private int type;
    private List<Image> images;

    private static final int SYSTEM_UI_SHOW = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    private static final int SYSTEM_UI_HIDE = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

    private boolean isSystemUiShown = true;

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferencesUtil.save(type + Constants.POSITION,currentPosition);
    }

    @Override
    protected void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
    }

    @Override
    protected void initLayoutId() {
        menuType = getIntent().getStringExtra(Constants.MENU_TYPE);
        layoutId = R.layout.activity_detail;

        if(TabFragment.MENU_PIC.equals(menuType)){
            isPicture = true;
            layoutId = R.layout.activity_detail_pulldown;
//            setTheme(R.style.Vie);
        }
    }

    @Override
    protected void initViews() {
        super.initViews();
        supportPostponeEnterTransition();
        int position = getIntent().getIntExtra(Constants.POSITION,0);
        currentPosition = position;

        List<Fragment> fragments = new ArrayList<>();

        if(TabFragment.MENU_NEWS.equals(menuType)){
            List<FreshPost> freshPosts = DB.findAllDateSorted(mRealm,FreshPost.class);
            adapter = new DetailPagerAdapter(getSupportFragmentManager(),fragments,freshPosts.size());

            for(int i = 0;i <DB.findAll(mRealm,FreshPost.class).size();i++){
                fragments.add(FreshDetailFragment.newInstance(i));
            }
        }

    }

    @SuppressWarnings("unchecked")


    @Override
    public void onPullStart() {

    }

    @Override
    public void onPull(float v) {

    }

    @Override
    public void onPullCancel() {

    }

    @Override
    public void onPullComplete() {

    }

    @Override
    public void onChange() {

    }

    private class DetailPagerAdapter extends FragmentStatePagerAdapter{

        private List<Fragment> fragments;
        private int size;

        public DetailPagerAdapter(FragmentManager fm,List<Fragment> fragments,int dataSize) {
            super(fm);
            this.fragments = fragments;
            this.size = dataSize;
        }


        public void addFragment(Fragment fragment){
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return size;
        }

        public ViewerFragment getCurrent(int position){
            return (ViewerFragment) adapter.instantiateItem(pager,position);
        }
    }
}
