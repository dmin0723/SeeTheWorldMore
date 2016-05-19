package myandroid.dengmin.seetheworldmore.mvp.view;

import myandroid.dengmin.seetheworldmore.R;
import myandroid.dengmin.seetheworldmore.ui.BaseFragment;
import myandroid.dengmin.seetheworldmore.utils.Constants;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by dmin on 2016/5/15.
 * A tab fragment contains different news fragment.
 */
public class TabFragment extends BaseFragment {

    private static final int SMOOTHSCROLL_TOP_POSITION = 50;
    public static final int TYPE_ZHIHU = 1024;
    public static final int TYPE_FRESH = 1025;

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    public static final String MENU_NEWS = "news";
    public static final String MENU_PIC = "pic";

    private List<RecyclerFragment> fragments = new ArrayList<>();
    private TabPagerAdapter adapter;
    private String menuType;

    //创建实例 String type
    public static TabFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constants.TYPE, type);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_news_tab;
    }

    @Override
    protected void initViews() {
        adapter = new TabPagerAdapter(getChildFragmentManager());
        initFragments();
        pager.setAdapter(adapter);

        if(MENU_PIC.equals(menuType)){
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        }

        tabs.setupWithViewPager(pager);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                scrollTop(fragments.get(tab.getPosition()).getRecyclerView());
            }
        });
    }

    private void scrollTop(RecyclerView list) {
        if(list != null){
            int lastPosition;
            if (MENU_PIC.equals(menuType)){
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) list.getLayoutManager();
                lastPosition = manager.findLastVisibleItemPositions(
                            new int[manager.getSpanCount()])[1];
            }else{
                LinearLayoutManager manager = (LinearLayoutManager) list.getLayoutManager();
                lastPosition = manager.findLastVisibleItemPosition();
            }
            if(lastPosition < SMOOTHSCROLL_TOP_POSITION){
                list.smoothScrollToPosition(0);
            }else{
                list.scrollToPosition(0);
            }
        }
    }

    private void initFragments() {
        menuType = getArguments().getString(Constants.TYPE);
        List<String> mTitles;

        if (MENU_PIC.equals(menuType)) {
            String[] titles = new String[]{
                    getString(R.string.gank),
                    getString(R.string.db_rank),
                    getString(R.string.db_leg),
                    getString(R.string.db_silk),
                    getString(R.string.db_breast),
                    getString(R.string.db_butt)};

            mTitles = Arrays.asList(titles);
            fragments.add(PictureFragment.newInstance(PictureFragment.TYPE_GANK));
            fragments.add(PictureFragment.newInstance(PictureFragment.TYPE_DB_RANK));
            fragments.add(PictureFragment.newInstance(PictureFragment.TYPE_DB_LEG));
            fragments.add(PictureFragment.newInstance(PictureFragment.TYPE_DB_SILK));
            fragments.add(PictureFragment.newInstance(PictureFragment.TYPE_DB_BREAST));
            fragments.add(PictureFragment.newInstance(PictureFragment.TYPE_DB_BUTT));

            if(fragments.size() != titles.length){
                throw new IllegalArgumentException("You need add all fragment in" + getClass().getSimpleName());
            }
        }else{
            mTitles = new ArrayList<>();
            fragments.add(new ZhihuFragment());
            fragments.add(new FreshFragment());
            mTitles.add(getString(R.string.zhihu_news));
            mTitles.add(getString(R.string.fresh_news));
        }
        adapter.setFragments(fragments,mTitles);
    }

    @Override
    protected void initData() {

    }

    public static class TabPagerAdapter extends FragmentPagerAdapter {

        private List<RecyclerFragment> fragments;
        private List<String> titles;

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setFragments(List<RecyclerFragment> fragments, List<String> titles) {
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }
    }
}
