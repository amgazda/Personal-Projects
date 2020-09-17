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
import java.util.Locale;

public class Machine extends LinearLayout {

    TextView name;
    TextView number;
    Button ss;
    TextView time;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    public Machine(String numberP, Context c, Button b, String mType, long mTime) {
        super(c);

        mTimeLeftInMillis = mTime;
        setOrientation(LinearLayout.HORIZONTAL);

        number = new TextView(c);
        /*int num = Integer.parseInt(numberP);
        String outputNum = String.format("%03s", num);*/
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(mType.equals("Washer: ")){
            number.setText(mType + numberP);
        }
        else {
            number.setText(mType + "    " + numberP);
        }
        number.setTextSize((int)width/50);
        number.setTextColor(Color.BLACK);
        number.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, .45f));
        addView(number);

        ss = new Button(c);
        ss.setTextColor(Color.BLACK);
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

    private void startTimer() {
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
                ss.setVisibility(View.INVISIBLE);
            }
        }.start();
        mTimerRunning = true;
        ss.setText("pause");
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        ss.setText("Start");
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        time.setText(timeLeftFormatted);
    }

}
