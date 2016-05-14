package android.dengmin.seetheworldmore.mvp.other;

import android.content.Context;
import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.mvp.interf.OnListFragmentInteract;
import android.dengmin.seetheworldmore.mvp.model.ZhihuJson;
import android.dengmin.seetheworldmore.mvp.model.ZhihuStory;
import android.dengmin.seetheworldmore.mvp.model.ZhihuTop;
import android.dengmin.seetheworldmore.net.DB;
import android.dengmin.seetheworldmore.ui.BaseActivity;
import android.dengmin.seetheworldmore.utils.Constants;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.Sort;

/**
 * Created by dmin on 2016/5/13.
 * Zhihu news' recyclerView adapter
 */
public class ZhihuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RealmChangeListener{

    private static final int TYPE_BANNER = 0;

    //header is a title to display date
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;

    //footer is to load more hint
    private static final int TYPE_FOOTER = 3;

    public static int textGrey;
    public static int textDark;

    private List<ZhihuStory> zhihustories;
    private List<ZhihuTop> tops;
    private Realm mRealm;
    public ConvenientBanner<ZhihuTop> banner;
    public OnListFragmentInteract mListener;

    public ZhihuListAdapter(OnListFragmentInteract listener, BaseActivity activity){
        mListener = listener;
        mRealm = activity.mRealm;
        mRealm.where(ZhihuJson.class).findAllSorted(Constants.DATE, Sort.DESCENDING);
        zhihustories = DB.findAll(mRealm,ZhihuStory.class);
        tops = DB.findAll(mRealm,ZhihuTop.class);
        mRealm.addChangeListener(this);
    }

    public void addNews(ZhihuJson news){
        notifyDataSetChanged();
    }

    public void clear(){
        banner.stopTurning();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_BANNER){
            View view = inflater.inflate(R.layout.fragment_news_banner,parent,false);
            return new BannerViewHolder(view);
        }else if(viewType == TYPE_HEADER){
            View view = inflater.inflate(R.layout.fragment_news_header,parent,false);
            return new HeaderViewHolder(view);
        }else if(viewType == TYPE_FOOTER){
            View view = inflater.inflate(R.layout.footer_loading,parent,false);
            return new FooterViewHolder(view);
        }else{
            View view = inflater.inflate(R.layout.fragment_fresh_item,parent,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        textGrey = ContextCompat.getColor(context,R.color.darker_gray);
        textDark = ContextCompat.getColor(context,android.support.design.R.color.abc_primary_text_material_light);

        if(holder instanceof ViewHolder){
            final ViewHolder viewHolder = (ViewHolder) holder;
            if(position == 1){
                viewHolder.header.setText(context.getString(R.string.hot));
                viewHolder.header.setVisibility(View.VISIBLE);
                viewHolder.mItem.setVisibility(View.GONE);
                viewHolder.mItem.setClickable(false);
                return;
            }else{
                //ViewHolder.zhihu
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_BANNER;
        }else if(position == zhihustories.size() +1){
            //position 0 is banner so
            //the footer appears the size + 1 position
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        //items + banner + footer
        return zhihustories.size() + 2;
    }

    @Override
    public void onChange() {
        if(banner != null){
            banner.notifyDataSetChanged();
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View view){
            super(view);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        public final TextView headerText;

        public HeaderViewHolder(View view){
            super(view);
            headerText = (TextView) view.findViewById(R.id.story_header);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        public final ConvenientBanner<ZhihuTop> banner;

        public BannerViewHolder(View view){
            super(view);
            banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView header;
        public final ImageView mImage;
        public final TextView mTitle;
        public final View mItem;
        public ZhihuStory zhihuStory;

        public ViewHolder(View view){
            super(view);
            header = (TextView) view.findViewById(R.id.story_header);
            mImage = (ImageView) view.findViewById(R.id.story_img);
            mTitle = (TextView) view.findViewById(R.id.news_title);
            mItem = view.findViewById(R.id.news_item);
        }
    }
}
