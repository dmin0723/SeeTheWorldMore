package android.dengmin.seetheworldmore;

import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import butterknife.Bind;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener{

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initLayoutId() {
       layoutId = R.layout.activity_main;
    }

    public DrawerLayout getDrawerLayout(){
        return drawerLayout;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
