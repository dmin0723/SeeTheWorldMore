package android.dengmin.seetheworldmore.mvp.other;


import android.content.Context;
import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.libraries.ArrayRecyclerAdapter;
import android.dengmin.seetheworldmore.libraries.RatioImageView;
import android.dengmin.seetheworldmore.mvp.model.Image;
import android.dengmin.seetheworldmore.utils.Imager;
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
