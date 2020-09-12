package com.example.binomialdfcalc10;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton, one, two, three, four, five;
    EditText trials;
    EditText prob;
    EditText successes;
    TextView answer;
    int s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String text = intent.getStringExtra(StartActivity.LANG);
        setAppLocale(text);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        trials = findViewById(R.id.numTrials);
        prob = findViewById(R.id.psucc);
        successes = findViewById(R.id.successes);
        answer = findViewById(R.id.respuesta);
        one = findViewById(R.id.radio_one);
        two = findViewById(R.id.radio_two);
        three = findViewById(R.id.radio_three);
        four = findViewById(R.id.radio_four);
        five = findViewById(R.id.radio_five);
        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if(Double.parseDouble(String.valueOf(prob.getText()))>1||Double.parseDouble(String.valueOf(prob.getText()))<0)
                {
                    answer.setText(R.string.probError);
                }
                else if(Double.parseDouble(String.valueOf(trials.getText()))<1)
                {
                    answer.setText(R.string.trialError);
                }
                else if(Double.parseDouble(String.valueOf(successes.getText()))>Double.parseDouble(String.valueOf(trials.getText())))
                {
                    answer.setText(R.string.successError);
                }
                else if(radioButton.equals(one))
                {
                    double res = 0;
                    for(int i = 0; i<=Integer.parseInt(String.valueOf(successes.getText()))-1; i++) {
                        BinomialCalculator b = new BinomialCalculator(Integer.parseInt(String.valueOf(trials.getText())),
                                Double.parseDouble(String.valueOf(prob.getText())),i);
                        res+=b.binomPDF();
                    }
                    answer.setText(String.format("%.3f%%", res*100));
                }
                else if(radioButton.equals(two))
                {
                    double res = 0;
                    for(int i = 0; i<=Integer.parseInt(String.valueOf(successes.getText())); i++) {
                        BinomialCalculator b = new BinomialCalculator(Integer.parseInt(String.valueOf(trials.getText())),
                                Double.parseDouble(String.valueOf(prob.getText())),i);
                        res+=b.binomPDF();
                    }
                    answer.setText(String.format("%.3f%%", res*100));
                }
                else if(radioButton.equals(three))
                {
                    double res = 0;
                    BinomialCalculator b = new BinomialCalculator(Integer.parseInt(String.valueOf(trials.getText())),
                            Double.parseDouble(String.valueOf(prob.getText())),Integer.parseInt(String.valueOf(successes.getText())));
                    res+=b.binomPDF();
                    answer.setText(String.format("%.3f%%", res*100));
                }
                else if(radioButton.equals(four))
                {
                    double res = 0;
                    for(int i = 0; i<=Integer.parseInt(String.valueOf(successes.getText()))-1; i++) {
                        BinomialCalculator b = new BinomialCalculator(Integer.parseInt(String.valueOf(trials.getText())),
                                Double.parseDouble(String.valueOf(prob.getText())),i);
                        res+=b.binomPDF();
                    }
                    answer.setText(String.format("%.3f%%", (1-res)*100));
                }
                else if(radioButton.equals(five))
                {
                    double res = 0;
                    for(int i = 0; i<=Integer.parseInt(String.valueOf(successes.getText())); i++) {
                        BinomialCalculator b = new BinomialCalculator(Integer.parseInt(String.valueOf(trials.getText())),
                                Double.parseDouble(String.valueOf(prob.getText())),i);
                        res+=b.binomPDF();
                    }
                    answer.setText(String.format("%.3f%%", (1-res)*100));
                }
                else
                {
                    System.out.println("Oops");
                }
            }
        });
        /*Switch sw = (Switch) findViewById(R.id.language);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setAppLocale("en");
                } else {
                    setAppLocale("es");
                }
            }
        });*/
    }
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        /*Toast.makeText(this, "Selected Radio Button: " + radioId,
                Toast.LENGTH_SHORT).show();*/
    }
    private void setAppLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }
}