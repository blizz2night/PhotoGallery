package personal.yulie.android.photogallery.dao;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import personal.yulie.android.photogallery.bean.Photo;

/**
 * Created by android on 17-8-9.
 */

public class PhotoDao {
    private static final String LOG_TAG = "DAO";
    private List<Photo> sPhotos;
    private final static PhotoDao sPhotoDao = new PhotoDao();

    private PhotoDao(){
        sPhotos = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Photo p = new Photo(i + ".jpg");
            sPhotos.add(p);
        }
    }

    public static PhotoDao getInstance() {
        return sPhotoDao;
    }

    public List<Photo> getPhotos() {
        return sPhotos;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static File getPicStorageDir() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    }
    public static String[] getAlbumNames(){
        File file = getPicStorageDir();
        return file.list();
    }
    public static File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(getPicStorageDir(), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }


}
