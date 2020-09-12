package com.example.binomialdfcalc10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class StartActivity extends AppCompatActivity {

    public static final String LANG = "com.example.binomialdfcalc10.LANG";
    Button buttonapply;
    RadioGroup rg;
    RadioGroup mode;
    RadioButton es;
    RadioButton en;
    RadioButton binom;
    RadioButton geom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        rg = findViewById(R.id.toggle);
        mode = findViewById(R.id.mode);
        es = findViewById(R.id.es);
        en = findViewById(R.id.en);
        binom = findViewById(R.id.binom);
        geom = findViewById(R.id.geom);
        Button buttonApply = findViewById(R.id.start);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startup();
            }
        });
    }

    public void startup(){
        Intent intent;
        int radioIdTwo = mode.getCheckedRadioButtonId();
        RadioButton buttonMode = findViewById(radioIdTwo);
        if(buttonMode.equals(binom)){
            intent = new Intent(this,MainActivity.class);}
        else {
            intent = new Intent(this,GeometActivity.class);}

        int radioId = rg.getCheckedRadioButtonId();
        RadioButton button = findViewById(radioId);
        if(button.equals(es)) {
            intent.putExtra(LANG, "es");
        }
        else {
            intent.putExtra(LANG, "en");}


        startActivity(intent);
    }


    }
