package com.example.laundrytimer;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.TextView;

public class Dryer extends Machine {

    TextView name;
    TextView number;
    Button ss;
    TextView time;
    static final long TIMESTART = 3600000L;
    public Dryer(String numberP, Context c, Button b) {
        super(numberP,c,b,"Dryer: ", TIMESTART);
    }

}
