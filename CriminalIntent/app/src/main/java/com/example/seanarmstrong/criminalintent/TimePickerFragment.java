package com.example.seanarmstrong.criminalintent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sean.armstrong on 16/03/2017.
 */

public class TimePickerFragment extends DialogFragment {

    private static final String ARG_DATE = "DATE";
    public static final String EXTRA_HOUR = "com.example.seanarmstrong.criminalintent.hour";
    public static final String EXTRA_MINUTE = "com.example.seanarmstrong.criminalintent.minute";


    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        setTimePickerTime(hour, minute);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = getTimePickerHour();
                        int minute = getTimePickerMinute();

                        sendResult(Activity.RESULT_OK, hour, minute);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, int hour, int minute) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_HOUR, hour);
        intent.putExtra(EXTRA_MINUTE, minute);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    private int getTimePickerHour() {
        if (newTimePickerApi()) {
            return mTimePicker.getHour();
        } else {
            return mTimePicker.getCurrentHour();
        }
    }

    private int getTimePickerMinute() {
        if (newTimePickerApi()) {
            return mTimePicker.getMinute();
        } else {
            return mTimePicker.getCurrentMinute();
        }
    }

    private void setTimePickerTime(int hour, int minute) {
        if (newTimePickerApi()) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }
    }

    private boolean newTimePickerApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
