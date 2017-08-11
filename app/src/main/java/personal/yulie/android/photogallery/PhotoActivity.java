package personal.yulie.android.photogallery;

import android.support.v4.app.Fragment;

/**
 * Created by android on 17-8-11.
 */

public class PhotoActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return PhotoFragment.newInstance();
    }
}
