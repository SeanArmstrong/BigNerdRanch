package com.example.seanarmstrong.criminalintent;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sean.armstrong on 25/02/2017.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
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

    public void setTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(mDate);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);

        mDate = calendar.getTime();
    }

    public String getFormattedDate() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(mDate);
    }

    public String getFormattedDateTime() {
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(mDate);
    }

    public void setDateMaintainTime(Date date) {
        Calendar previousCalendar = Calendar.getInstance();
        Calendar newCalendar = Calendar.getInstance();

        previousCalendar.setTime(mDate);
        newCalendar.setTime(date);

        newCalendar.set(Calendar.HOUR, previousCalendar.get(Calendar.HOUR));
        newCalendar.set(Calendar.MINUTE, previousCalendar.get(Calendar.MINUTE));

        mDate = newCalendar.getTime();
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
