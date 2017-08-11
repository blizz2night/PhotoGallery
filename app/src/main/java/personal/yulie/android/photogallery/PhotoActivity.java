package personal.yulie.android.photogallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import personal.yulie.android.photogallery.bean.Photo;
import personal.yulie.android.photogallery.dao.PhotoDao;

/**
 * Created by android on 17-8-11.
 */

public class PhotoActivity extends AppCompatActivity {
    private static final String EXTRA_PHOTO_POS =
            "personal.yulie.android.photogallery.PhotoActivity.pos";
    private ViewPager mViewPager;
    private List<Photo> mPhotos;
    private Photo mPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        mPhotos = PhotoDao.getInstance().getPhotos();
        mViewPager = (ViewPager) findViewById(R.id.activity_photo_view_pager);
        int pos = getIntent().getIntExtra(EXTRA_PHOTO_POS, 20);
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return PhotoFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return mPhotos.size();
            }
        });
        mViewPager.setCurrentItem(pos);
    }

    public static Intent newIntent(Context context, int position){
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO_POS, position);
        return intent;
    }
}
