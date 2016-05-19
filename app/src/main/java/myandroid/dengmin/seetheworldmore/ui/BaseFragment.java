package myandroid.dengmin.seetheworldmore.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by dmin on 2016/5/12.
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected int layoutId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       if(rootView == null){
           initLayoutId();
           rootView = inflater.inflate(layoutId,container,false);
           ButterKnife.bind(this,rootView);
           initViews();
       }
        AlwaysInit();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initData();

    protected void AlwaysInit() {
        ButterKnife.bind(this,rootView);
    }

    protected abstract void initViews();

    protected abstract void initLayoutId();

    public boolean isAlive(){
        return getActivity() != null;
    }
}
