package personal.yulie.android.photogallery.bean;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by android on 17-8-9.
 */

public class Photo {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private File mPic;

    public Photo(){
        mId = UUID.randomUUID();
    }

    public Photo(Date date) {
        this();
        mDate = date;
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

    public UUID getId() {
        return mId;
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

