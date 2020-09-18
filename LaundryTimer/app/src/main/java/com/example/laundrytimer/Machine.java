package com.example.laundrytimer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;

public class Machine extends LinearLayout {

    //TextView name;
    transient TextView number;
    transient Button ss;
    transient TextView time;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long mEndTime;
    private String tag;
    public Machine(String numberP, Context c, Button b, String mType, long mTime, boolean run) {
        super(c);

        mTimeLeftInMillis = mTime;
        mTimerRunning = run;
        setOrientation(LinearLayout.HORIZONTAL);
        //mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        tag = numberP;
        number = new TextView(c);
        /*int num = Integer.parseInt(numberP);
        String outputNum = String.format("%03s", num);*/
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(mType.equals("Washer: ")){
            number.setText(numberP);
        }
        else {
            number.setText(numberP);
        }
        number.setTextSize((int)width/50);
        number.setTextColor(Color.BLACK);
        number.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, .45f));
        addView(number);

        ss = new Button(c);
        ss.setTextColor(Color.WHITE);
        ss.setBackgroundResource(R.drawable.laundry_button);
        ss.setText("Start");
        ss.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,.1f));
        addView(ss);

        time = new TextView(c);
        time.setText("");
        time.setTextSize((int)width/50);
        time.setTextColor(Color.BLACK);
        time.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,.45f));
        addView(time);

        addView(b);

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        updateCountDownText();
    }

    public void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                ss.setText("Start");
                //ss.setVisibility(View.INVISIBLE);
            }
        }.start();
        mTimerRunning = true;
        ss.setText("Pause");
    }
    public void pauseTimer() {
        if(mCountDownTimer!=null) {
        mCountDownTimer.cancel();}
        mTimerRunning = false;
        ss.setText("Start");
    }

    public void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        time.setText(timeLeftFormatted);
    }

    public long getmTimeLeftInMillis() {
        return mTimeLeftInMillis;
    }

    public void setmTimeLeftInMillis(long l) {
        mTimeLeftInMillis = l;
    }

    public long getEndTime() {
        return mEndTime;
    }

    public String getTag() {
        return tag;
    }

    public boolean getRun() {
        return mTimerRunning;
    }

    public void setRun(boolean b) {
        mTimerRunning = b;
    }

    public CountDownTimer getmCountDownTimer() {
        return mCountDownTimer;
    }



}
