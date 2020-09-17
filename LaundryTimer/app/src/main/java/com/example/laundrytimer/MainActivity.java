package com.example.laundrytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button washer;
    Button dryer;
    LinearLayout ll;
    EditText et;
    CheckBox cb;
    HashMap<String, Machine> map = new HashMap<String, Machine>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        washer = (Button) findViewById(R.id.washer);
        dryer = (Button) findViewById(R.id.dryer);
        ll = (LinearLayout) findViewById(R.id.ll);
        et = (EditText) findViewById(R.id.enter);
        cb = (CheckBox) findViewById(R.id.cb);

        washer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!et.getText().toString().equals("")&&map.size()<8&&!map.containsKey(String.format("%02d", Integer.parseInt(et.getText().toString())))) {
                    if (!map.containsKey(et.getText().toString())) {
                        final Button remove = new Button(MainActivity.this);
                        String formNum = String.format("%02d", Integer.parseInt(et.getText().toString()));
                        remove.setText("Remove: " + formNum);
                        remove.setTextColor(Color.WHITE);
                        remove.setBackgroundResource(R.drawable.laundry_button);
                        remove.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        Washer w = new Washer(formNum, MainActivity.this, remove);
                        map.put(formNum, w);
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(cb.isChecked()) {
                                    ll.removeView(map.get(remove.getText().toString().substring(remove.getText().toString().indexOf(":") + 2)));
                                    map.remove(remove.getText().toString().substring(remove.getText().toString().indexOf(":") + 2));
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
                else if(map.containsKey(String.format("%02d", Integer.parseInt(et.getText().toString())))) {
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
                if(!et.getText().toString().equals("")&&map.size()<8&&!map.containsKey(String.format("%02d", Integer.parseInt(et.getText().toString())))) {
                    if (!map.containsKey(et.getText().toString())) {
                        final Button remove = new Button(MainActivity.this);
                        String formNum = String.format("%02d", Integer.parseInt(et.getText().toString()));
                        remove.setText("Remove: " + formNum);
                        remove.setTextColor(Color.WHITE);
                        remove.setBackgroundResource(R.drawable.laundry_button);
                        remove.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        Dryer d = new Dryer(formNum, MainActivity.this, remove);
                        map.put(formNum, d);
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(cb.isChecked()) {
                                    ll.removeView(map.get(remove.getText().toString().substring(remove.getText().toString().indexOf(":") + 2)));
                                    map.remove(remove.getText().toString().substring(remove.getText().toString().indexOf(":") + 2));
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
                else if(map.containsKey(String.format("%02d", Integer.parseInt(et.getText().toString())))) {
                    Toast.makeText(getApplicationContext(), "Machine already added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}