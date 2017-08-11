package personal.yulie.android.photogallery.bean;

import java.io.File;
import java.util.Date;

/**
 * Created by android on 17-8-9.
 */

public class Photo {
    private String mTitle;
    private Date mDate;
    private File mPic;

    public Photo(){
        mDate = new Date();
    }

    public Photo(Date date) {
        mDate = date;
    }

    public Photo(String title) {
        this();
        mTitle = title;
    }

    public Photo(String title, Date date) {
        this(date);
        mTitle = title;
    }

    public Photo(String title, File pic) {
        this(title, new Date(pic.lastModified()));
        mPic = pic;
    }

    public Photo(File pic) {
        this(pic.getName(), pic);
        mPic = pic;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public File getPic() {
        return mPic;
    }
}

