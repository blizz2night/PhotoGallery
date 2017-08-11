package personal.yulie.android.photogallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

import personal.yulie.android.photogallery.bean.Photo;
import personal.yulie.android.photogallery.dao.PhotoDao;
import personal.yulie.android.photogallery.utils.BitmapUtils;

import static personal.yulie.android.photogallery.PhotoGalleryFragment.LOG_TAG;

/**
 * Created by android on 17-8-11.
 */

public class PhotoFragment extends Fragment {
    public static final String ARG_ID = "arg_id";
    public static final String ARG_POS = "arg_pos";

    private Photo mPhoto;
    private ImageView mImageView;
    private TextView mTitle;

    public static PhotoFragment newInstance(int position) {
        Bundle args = new Bundle();
        Photo photo = PhotoDao.getInstance().get(position);
        args.putSerializable(ARG_ID, photo.getId());
        args.putInt(ARG_POS,position);
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        UUID id = (UUID) args.getSerializable(ARG_ID);
        int pos = args.getInt(ARG_POS);
        mPhoto = PhotoDao.getInstance().get(pos);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        mImageView = view.findViewById(R.id.fragment_photo_image_view);
        Log.i(LOG_TAG,mPhoto.getPic().getAbsolutePath());
        view.post(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapUtils.getBitmap(
                        mPhoto.getPic().getAbsolutePath(),
                        mImageView.getWidth(),
                        mImageView.getHeight());
                mImageView.setImageBitmap(bitmap);
            }
        });
        mTitle = view.findViewById(R.id.fragment_photo_text_view);
        mTitle.setText(mPhoto.getTitle());
        return view;
    }
}
