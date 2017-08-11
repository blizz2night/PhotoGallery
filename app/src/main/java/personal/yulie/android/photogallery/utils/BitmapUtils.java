package personal.yulie.android.photogallery.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by android on 17-8-11.
 */

public class BitmapUtils {
    public static Bitmap getBitmap(String filePath, int destWidth, int destHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;

        Bitmap orgin = BitmapFactory.decodeFile(filePath, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            float hScale = srcHeight / destHeight;
            float wScale = srcWidth / destWidth;
            inSampleSize = Math.round(hScale > wScale ? hScale : wScale);
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,options);
        return bitmap;
    }
}
