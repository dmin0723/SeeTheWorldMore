package android.dengmin.seetheworldmore.mvp.other;

import android.dengmin.seetheworldmore.mvp.interf.OnlistFragmentInteract;
import android.dengmin.seetheworldmore.mvp.model.FreshPost;
import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;

/**
 * Created by dmin on 2016/5/13.
 * Fresh news's recycleView adapter
 */
public class FreshListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Realm realm;
    private List<FreshPost> freshPosts;
    private OnlistFragmentInteract mListener;

    public FreshListAdapter(OnlistFragmentInteract listener, BaseActivity activity) {
        mListener = listener;
        realm = activity.mRealm;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
