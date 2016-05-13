package android.dengmin.seetheworldmore.ui;

import android.content.Intent;
import android.dengmin.seetheworldmore.BuildConfig;
import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.utils.IntentUtil;
import android.dengmin.seetheworldmore.utils.SnackUtil;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by dmin on 2016/5/12.
 */
public class AboutActivity extends BaseActivity{
    @Bind(R.id.versionName)
    TextView versionName;
    //格式错误

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_about;
    }

    @Override
    protected void initViews() {
        super.initViews();
        versionName.append(" " + BuildConfig.VERSION_NAME);//""原来没有空格，出现编译错误，但不报这里
    }

    @Override
    public void startActivity(Intent intent) {
        if(IntentUtil.isIntentSafe(intent)){
            super.startActivity(intent);
        }else{
            SnackUtil.showSnack(versionName,R.string.email_not_install);
        }
    }
}
