package android.dengmin.seetheworldmore.utils;

import java.math.BigDecimal;

/**
 * Created by dmin on 2016/5/12.
 */
public class FileUtil {

    public static long getFileLength(java.io.File dir){
        long length = 0;
        for(java.io.File file : dir.listFiles()){
            if(file.isFile()){
                length += file.length();
            }else{
                length += getFileLength(file);
            }
        }
        return length;
    }

    public static String getFileSize(java.io.File dir){
        BigDecimal bigDecimal;
        if(getFileLength(dir) > 1024 * 1024){
            bigDecimal = new BigDecimal(getFileLength(dir) / 1000000);
            return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_EVEN) + "M";
        }else{
            //length of file is less than 1 mb,use k as a unit
            bigDecimal = new BigDecimal(getFileLength(dir) / 1000);
            return bigDecimal.setScale(0,BigDecimal.ROUND_HALF_EVEN) + "K";
        }
    }
}
