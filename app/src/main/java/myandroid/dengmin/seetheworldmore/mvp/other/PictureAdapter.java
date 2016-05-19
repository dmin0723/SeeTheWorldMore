package myandroid.dengmin.seetheworldmore.mvp.other;


import android.content.Context;
import myandroid.dengmin.seetheworldmore.R;
import myandroid.dengmin.seetheworldmore.libraries.ArrayRecyclerAdapter;
import myandroid.dengmin.seetheworldmore.libraries.RatioImageView;
import myandroid.dengmin.seetheworldmore.mvp.model.Image;
import myandroid.dengmin.seetheworldmore.utils.Imager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dmin on 2016/5/13.
 */
public abstract class PictureAdapter extends ArrayRecyclerAdapter<Image,PictureAdapter.ViewHolder> {

    private Context context;

    public PictureAdapter(Context context) {
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    protected abstract void onItemClick(View v, int position);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = get(position);
        holder.imageView.setOriginalSize(image.getWidth(),image.getHeight());
        Imager.load(holder.itemView.getContext(),image.getUrl(),holder.imageView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RatioImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (RatioImageView) itemView.findViewById(R.id.picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick(view,getAdapterPosition());
                }
            });
        }
    }
}
