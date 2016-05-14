package android.dengmin.seetheworldmore.mvp.other;

import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.mvp.interf.OnListFragmentInteract;
import android.dengmin.seetheworldmore.mvp.model.FreshPost;
import android.dengmin.seetheworldmore.net.DB;
import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.dengmin.seetheworldmore.utils.Imager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;

/**
 * Created by dmin on 2016/5/13.
 * Fresh news's recycleView adapter
 */
public class FreshListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Realm realm;
    private List<FreshPost> freshPosts;
    private OnListFragmentInteract mListener;

    public FreshListAdapter(OnListFragmentInteract listener, BaseActivity activity) {
        mListener = listener;
        realm = activity.mRealm;
        freshPosts = DB.findAllDateSorted(realm,FreshPost.class);
    }

    public void addNews(){
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fresh_item,parent,false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.freshPost = freshPosts.get(position);
        String imgUrl = viewHolder.freshPost.getCustom_fields().getThumb_c().get(0).getRealmString();
        Imager.load(viewHolder.itemView.getContext(),imgUrl,viewHolder.mImage);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.OnListFragmentInteraction(viewHolder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return freshPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mImage;
        public final TextView mTitle;
        public final View mItem;
        public FreshPost freshPost;

        public ViewHolder(View view) {
            super(view);
            mImage = (ImageView) view.findViewById(R.id.img_story);
            mTitle = (TextView) view.findViewById(R.id.news_title);
            mItem = view.findViewById(R.id.news_item);
        }

        @Override
        public String toString() {
            return super.toString() + " ' " + mTitle.getText() + " ' ";
        }
    }
}
