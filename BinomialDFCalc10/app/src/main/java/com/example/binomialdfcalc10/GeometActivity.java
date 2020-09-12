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

public class GeometActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton, one, two, three, four, five;
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
        setContentView(R.layout.activity_geomet);
        radioGroup = findViewById(R.id.radioGroup);
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
                GeometricCalculator g = new GeometricCalculator(Double.parseDouble(String.valueOf(prob.getText())),
                        Integer.parseInt(String.valueOf(successes.getText())));
                if(Double.parseDouble(String.valueOf(prob.getText()))>1||Double.parseDouble(String.valueOf(prob.getText()))<0)
                {
                    answer.setText(R.string.probError);
                }
                else if(Double.parseDouble(String.valueOf(successes.getText()))<0)
                {
                    answer.setText(R.string.geomError);
                }
                else if(radioButton.equals(one))
                {
                    double res = 0;
                    res = g.lt();
                    answer.setText(String.format("%.3f%%", res*100));
                }
                else if(radioButton.equals(two))
                {
                    double res = 0;
                    res  = 1-g.gt();
                    answer.setText(String.format("%.3f%%", res*100));
                }
                else if(radioButton.equals(three))
                {
                    double res = 0;
                    res = g.et();
                    answer.setText(String.format("%.3f%%", res*100));
                }
                else if(radioButton.equals(four))
                {
                    double res = 0;
                    res = 1-g.lt();
                    answer.setText(String.format("%.3f%%", (res)*100));
                }
                else if(radioButton.equals(five))
                {
                    double res = 0;
                    res = g.gt();
                    answer.setText(String.format("%.3f%%", (res)*100));
                }
                else
                {
                    System.out.println("Oops");
                }
            }
        });
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