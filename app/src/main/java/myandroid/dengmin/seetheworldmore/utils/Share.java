package myandroid.dengmin.seetheworldmore.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by dmin on 2016/5/12.
 * Util to share date,make share intent,etc.
 */
public class Share {

    public static Intent getShareIntent(String shareText){
        Intent textIntent = new Intent();
        textIntent.setAction(Intent.ACTION_SEND);
        textIntent.putExtra(Intent.EXTRA_TEXT,shareText);
        textIntent.setType("text/plain");
        return textIntent;
    }

    public static Intent getShareImageIntent(Uri uri){
        Intent imageIntent = new Intent();
        imageIntent.setAction(Intent.ACTION_SEND);
        imageIntent.putExtra(Intent.EXTRA_STREAM,uri);
        imageIntent.setType("image/*");
        return imageIntent;
    }

    public static Intent getShareHtmlIntent(String htmlText){
        Intent textIntent = new Intent();
        textIntent.setAction(Intent.ACTION_SEND);
        textIntent.putExtra(Intent.EXTRA_TEXT,"This is html");
        textIntent.putExtra(Intent.EXTRA_HTML_TEXT,htmlText);
        textIntent.setType("text/plain");
        return textIntent;
    }

    public static void shareText(Context context,String text){
        context.startActivity(
                Intent.createChooser(getShareIntent(text),
                        "分享到"));//context.getString(R.string.share_to) NG
    }

    public static void shareImage(Context context,Uri uri){
        context.startActivity(
                Intent.createChooser(getShareImageIntent(uri),
                        "分享到"));//context.getString(R.string.share_to) NG
    }

    public static void shareHtmlText(Context context,String htmlText){
        context.startActivity(
                Intent.createChooser(getShareHtmlIntent(htmlText),
                        "分享到"));//context.getString(R.string.share_to) NG
    }
}
