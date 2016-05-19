package myandroid.dengmin.seetheworldmore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;
import io.realm.Realm;
import myandroid.dengmin.seetheworldmore.R;

/**
 * Created by dmin on 2016/5/12.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Realm mRealm;
    protected int layoutId = R.layout.activity_base;
    protected Toolbar toolbar;
    //private boolean isShowToolabr = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initLayoutId();
        super.onCreate(savedInstanceState);
        initViews();
    }

    protected abstract void initLayoutId();

    protected void initViews() {
        setContentView(layoutId);

        ButterKnife.bind(this);

        mRealm = Realm.getDefaultInstance();

        initAppBar();

        //initSDK(); 这是留给友盟的
    }

    public void initAppBar() {
        //具体指向哪一个toolbar是看哪一个activity调用
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    //切换一个具体内容的fragment视图
    public void replaceFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment,tag);//具体到内容布局了
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
        mRealm.close();
    }

}
