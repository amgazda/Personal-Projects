package com.example.laundrytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    transient Button washer;
    transient Button dryer;
    transient LinearLayout ll;
    transient EditText et;
    transient CheckBox cb;
    File file = new File("saveFile");
    HashMap<String, Machine> map = new HashMap<String, Machine>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadData();
        washer = (Button) findViewById(R.id.washer);
        dryer = (Button) findViewById(R.id.dryer);
        ll = (LinearLayout) findViewById(R.id.ll);
        et = (EditText) findViewById(R.id.enter);
        cb = (CheckBox) findViewById(R.id.cb);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;

        washer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!et.getText().toString().equals("")&&map.size()<8&&!map.containsKey(String.format("Washer: %02d", Integer.parseInt(et.getText().toString())))) {
                    if (!map.containsKey(et.getText().toString())) {
                        final Button remove = new Button(MainActivity.this);
                        final String formNum = String.format("%02d", Integer.parseInt(et.getText().toString()));
                        remove.setText("Remove: " + formNum);
                        remove.setTextColor(Color.WHITE);
                        remove.setBackgroundResource(R.drawable.laundry_button);
                        remove.setLayoutParams(new LayoutParams((int)width/4, LayoutParams.WRAP_CONTENT, .5f));
                        Washer w = new Washer("Washer: " + formNum, MainActivity.this, remove);
                        map.put("Washer: " + formNum, w);
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(cb.isChecked()) {
                                    ll.removeView(map.get("Washer: " + formNum));
                                    map.remove("Washer: " + formNum);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Enter Delete Mode", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        ll.addView(w);
                    }
                }
                else if(et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a machine number", Toast.LENGTH_SHORT).show();
                }
                else if(map.size()>=8) {
                    Toast.makeText(getApplicationContext(), "Max capacity reached", Toast.LENGTH_SHORT).show();
                }
                else if(map.containsKey(String.format("Washer: %02d", Integer.parseInt(et.getText().toString())))) {
                    Toast.makeText(getApplicationContext(), "Machine already added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });

        dryer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!et.getText().toString().equals("")&&map.size()<8&&!map.containsKey(String.format("Dryer:  %02d", Integer.parseInt(et.getText().toString())))) {
                    if (!map.containsKey(et.getText().toString())) {
                        final Button remove = new Button(MainActivity.this);
                        final String formNum = String.format("%02d", Integer.parseInt(et.getText().toString()));
                        remove.setText("Remove: " + formNum);
                        remove.setTextColor(Color.WHITE);
                        remove.setBackgroundResource(R.drawable.laundry_button);
                        remove.setLayoutParams(new LayoutParams((int)width/4, LayoutParams.WRAP_CONTENT, .5f));
                        Dryer d = new Dryer("Dryer: " + " " + formNum, MainActivity.this, remove);
                        map.put("Dryer: " + " " + formNum, d);
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(cb.isChecked()) {
                                    ll.removeView(map.get("Dryer: " + " " + formNum));
                                    map.remove("Dryer: " + " " + formNum);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Enter Delete Mode", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        ll.addView(d);
                    }
                }
                else if(et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a machine number", Toast.LENGTH_SHORT).show();
                }
                else if(map.size()>=8) {
                    Toast.makeText(getApplicationContext(), "Max capacity reached", Toast.LENGTH_SHORT).show();
                }
                else if(map.containsKey(String.format("Dryer:  %02d", Integer.parseInt(et.getText().toString())))) {
                    Toast.makeText(getApplicationContext(), "Machine already added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("set", map.keySet());
        for(Map.Entry<String,Machine> entry : map.entrySet()) {
            editor.putLong(entry.getKey() + "left", entry.getValue().getmTimeLeftInMillis());
            editor.putLong(entry.getKey() + "end", entry.getValue().getEndTime());
            editor.putBoolean(entry.getKey() + "bool", entry.getValue().getRun());
            if (entry.getValue().getmCountDownTimer() != null) {
                entry.getValue().getmCountDownTimer().cancel();
            }
        }
        ll.removeAllViews();
        editor.apply();


    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        Set<String> sos = (HashSet<String>) prefs.getStringSet("set", new HashSet<String>());
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;
        for(final String temp:sos) {
            final Button remove = new Button(MainActivity.this);
            String formNum = String.format("%02d", Integer.parseInt(
                    temp.substring(temp.length()-2)));
            remove.setText("Remove: " + formNum);
            remove.setTextColor(Color.WHITE);
            remove.setBackgroundResource(R.drawable.laundry_button);
            remove.setLayoutParams(new LayoutParams((int)width/4, LayoutParams.WRAP_CONTENT, .5f));
            boolean tempoB = prefs.getBoolean(temp + "bool", false);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cb.isChecked()) {
                        ll.removeView(map.get(temp));
                        map.remove(temp);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Enter Delete Mode", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if(temp.charAt(0)=='W') {
                long tempoL = prefs.getLong(temp + "end", 1800000L);
                long tLeft = prefs.getLong(temp + "left", 1800000L);
                if(tempoB) {
                    Washer tempW = new Washer(temp, MainActivity.this, remove, tempoL - System.currentTimeMillis(), tempoB);
                    map.put(temp, tempW);
                    ll.addView(tempW);
                    if (tempoL - System.currentTimeMillis() < 0) {
                        map.get(temp).setmTimeLeftInMillis(0);
                        map.get(temp).setRun(false);
                        map.get(temp).updateCountDownText();
                        map.get(temp).setColorExpired();
                    } else {
                        map.get(temp).startTimer();
                    }
                }
                else {
                    Washer tempW = new Washer(temp, MainActivity.this, remove, tLeft, tempoB);
                    map.put(temp, tempW);
                    ll.addView(tempW);
                    if(tLeft<1000) {
                        map.get(temp).setColorExpired();
                    }


                }
            }
            else {
                long tempoL = prefs.getLong(temp + "end", 3600000L);
                long tLeft = prefs.getLong(temp + "left", 3600000L);
                if(tempoB) {
                    Dryer tempD = new Dryer(temp, MainActivity.this, remove, tempoL - System.currentTimeMillis(), tempoB);
                    map.put(temp, tempD);
                    ll.addView(tempD);
                    if (tempoL - System.currentTimeMillis() < 0) {
                        map.get(temp).setmTimeLeftInMillis(0);
                        map.get(temp).setRun(false);
                        map.get(temp).updateCountDownText();
                        map.get(temp).setColorExpired();
                    } else {
                        map.get(temp).startTimer();
                    }
                }
                else {
                    Dryer tempD = new Dryer(temp, MainActivity.this, remove, tLeft, tempoB);
                    map.put(temp, tempD);
                    ll.addView(tempD);
                    if(tLeft<1000) {
                        map.get(temp).setColorExpired();
                    }
                }

            }
        }


    }

}