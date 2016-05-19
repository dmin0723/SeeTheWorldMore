package android.dengmin.seetheworldmore;

import android.content.Intent;
import android.dengmin.seetheworldmore.mvp.view.TabFragment;
import android.dengmin.seetheworldmore.ui.AboutActivity;
import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.dengmin.seetheworldmore.ui.SettingsActivity;
import android.dengmin.seetheworldmore.utils.Imager;
import android.dengmin.seetheworldmore.utils.Share;
import android.dengmin.seetheworldmore.utils.UI;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.Bind;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener{

    @Bind(R.id.navigation_view)
    NavigationView navView;//侧滑页面

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;//主页面

    boolean backPressed;//主页是否返回
    private String currentType;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override //源至BaseActivity abstract
    protected void initLayoutId() {
       layoutId = R.layout.activity_main;
    }

    @Override //源至BaseActivity
    protected void initViews() {
        super.initViews();
        setupDrawer();//设置toggle,关联drawer and navigation
        initNavigationView();//初始化nav
        replace(TabFragment.MENU_NEWS);//确定具体的fragment
//        initSDK();//友盟
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    private void replace(String type) {
        if(!type.equals(currentType)){
            currentType = type;
            replaceFragment(TabFragment.newInstance(type),type);
        }
    }

    //初始化侧滑
    private void initNavigationView() {
        //加载header图片 对图片下载进行封装，做为一个工具类
        Imager.load(this,R.drawable.head, (ImageView) navView.getHeaderView(0).findViewById(R.id.headImage));

        //添加了点击的接口，实现navigation的点击功能
        navView.setNavigationItemSelectedListener(this);

        //加载menu
        navView.inflateMenu(R.menu.main_drawer);

        //select the first menu at startup
        Menu menu = navView.getMenu();
        menu.getItem(0).setChecked(true);//默认为知识

        //添加图标
        menu.getItem(0).setIcon(R.drawable.explore);
        menu.getItem(1).setIcon(R.drawable.face);

        //给子menu也 添加图标
        Menu sub = menu.getItem(2).getSubMenu();
        sub.getItem(0).setIcon(R.drawable.share);
        sub.getItem(1).setIcon(R.drawable.settings);
    }

    //侧滑菜单的点击事件
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_knowledge:
                replace(TabFragment.MENU_NEWS);
                break;
            case R.id.nav_beauty:
                replace(TabFragment.MENU_PIC);
                break;
            case R.id.nav_share:
                Share.shareText(this,getString(R.string.share_app_description));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //设置toggle
    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    //back返回事件
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            doublePressBackToQuit();
        }
    }

    private void doublePressBackToQuit() {
        //先退
        if(backPressed){
            super.onBackPressed();//关闭acitivity and fragment
            return;
        }

        //两秒内
        backPressed = true;
        UI.showSnack(drawerLayout,R.string.leave_app);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = false;
            }
        },2000);
    }

    //创建“关于”的菜单项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    //增加“关于”的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_about){
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //不懂？
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        supportPostponeEnterTransition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
