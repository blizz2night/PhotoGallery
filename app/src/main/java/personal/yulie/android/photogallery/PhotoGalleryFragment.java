package personal.yulie.android.photogallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import personal.yulie.android.photogallery.bean.Photo;
import personal.yulie.android.photogallery.dao.PhotoDao;

/**
 * Created by android on 17-8-9.
 */

public class PhotoGalleryFragment extends Fragment {
    public static final String LOG_TAG = "PhotoGalleryFragment";
    public static final String CAMERA_FOLDER = "Camera";
    public static final int REQUEST_ALBUM = 0;
    private RecyclerView mGalleryRecyclerView;
    private GalleryAdapter mGalleryAdapter;

    public static PhotoGalleryFragment newInstance() {
        Bundle args = new Bundle();
        PhotoGalleryFragment fragment = new PhotoGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mGalleryRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_photo_gallery);
        mGalleryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PhotoDao photoDao = PhotoDao.getInstance();
        List<Photo> photos = photoDao.getPhotos();
        if (PhotoDao.isExternalStorageReadable()) {
            File dir = PhotoDao.getPicStorageDir();
            Log.i(LOG_TAG, PhotoDao.getPicStorageDir().toString());
            if (ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            String[] Albums = PhotoDao.getAlbumNames();
            for (String s : Albums) {
                Log.i(LOG_TAG, s);
            }
            File file = new File(dir, "Camera");
            if (file.exists()) {
                String[] pics = file.list();
                for (String pic :
                        pics) {
                    Log.i(LOG_TAG, pic);
                }
                Log.i(LOG_TAG, String.valueOf(pics.length));
            }

        }
        mGalleryAdapter = new GalleryAdapter(photos);
        mGalleryRecyclerView.setAdapter(mGalleryAdapter);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Log.i(PhotoGalleryFragment.LOG_TAG, uri.toString());
    }

    private class GalleryViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDate;
        private Photo mPhoto;

        public GalleryViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_photo_item, parent, false));
            //itemView == inflater.inflate(...)
            mTitle = (TextView) itemView.findViewById(R.id.galley_title);
            mDate = (TextView) itemView.findViewById(R.id.galley_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_ALBUM);
                }
            });
        }

        public void bind(Photo photo) {
            mPhoto = photo;
            mTitle.setText(mPhoto.getTitle());
            mDate.setText(mPhoto.getDate().toString());
        }
    }

    private class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
        private List<Photo> mPhotos;

        public GalleryAdapter(List<Photo> photos) {
            mPhotos = photos;
        }

        //Adapter will create enough ViewHolders to fill the screen and reuse them
        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new GalleryViewHolder(inflater,parent);
        }

        //Feed the data to ViewHolder
        @Override
        public void onBindViewHolder(GalleryViewHolder holder, int position) {
            holder.bind(mPhotos.get(position));
        }

        @Override
        public int getItemCount() {
            return mPhotos.size();
        }
    }

}
