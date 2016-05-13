package android.dengmin.seetheworldmore.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.dengmin.seetheworldmore.MainActivity;
import android.dengmin.seetheworldmore.R;
import android.dengmin.seetheworldmore.mvp.model.ZhihuTop;
import android.dengmin.seetheworldmore.utils.Constants;
import android.dengmin.seetheworldmore.utils.Imager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;//查阅学习用法

/**
 * Created by dmin on 2016/5/12.
 */
public class BannerView implements Holder<ZhihuTop>{
    private View view;

    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).
                inflate(R.layout.card_item_big,null);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, int position, final ZhihuTop data) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.story_img);
        TextView textView = (TextView) view.findViewById(R.id.news_title);
        Imager.loadWithHighPriority(data.getImage(),imageView);
        textView.setText(data.getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ZhihuDetailActivity.class);
                intent.putExtra(Constants.ID,data.getId());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((MainActivity)context,imageView,"shared_img");
                ActivityCompat.startActivity((MainActivity)context,intent,optionsCompat.toBundle());
            }
        });
    }
}
