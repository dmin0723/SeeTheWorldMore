package myandroid.dengmin.seetheworldmore.mvp.view;

import myandroid.dengmin.seetheworldmore.R;
import myandroid.dengmin.seetheworldmore.libraries.TouchImageView;
import myandroid.dengmin.seetheworldmore.ui.BaseFragment;
import android.graphics.Bitmap;
import android.view.View;

import butterknife.Bind;

/**
 * Created by dmin on 2016/5/16.
 * Photo view fragment.
 */
public class ViewerFragment extends
        BaseFragment implements View.OnLongClickListener,View.OnClickListener{

   private static final String TAG = "test";
    @Bind(R.id.headImage)
    TouchImageView imageView;
    private String url;
    private DetailActivity activity;
    private Bitmap bitmap;


    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_viewer;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
