package personal.yulie.android.photogallery.bean;

import java.util.Date;

/**
 * Created by android on 17-8-9.
 */

public class Photo {
    private String mTitle;
    private Date mDate;

    public Photo(){
        mDate = new Date();
    }

    public Photo(String title) {
        this();
        mTitle = title;
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
}
