package personal.yulie.android.photogallery;

import android.content.Context;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by android on 17-8-10.
 */
public class PhotoGalleryFragmentTest {
    private static final String LOG_TAG = "UnitTest";

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void test(){
        Context appContext = InstrumentationRegistry.getTargetContext();
//        String type = intent.getType();
//        String path = "";
//        Uri uri = data.getData();
//        if (!TextUtils.isEmpty(uri.getAuthority())) {
//            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//            if (cursor != null) {
//                int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                if (cursor.moveToFirst()) {
//                    path = cursor.getString(index);
//                    cursor.close();
//                }
//            }
//        }else {
//            path = uri.getPath();
//        }

        Log.i(LOG_TAG, Environment.getDataDirectory().toString());
    }
}