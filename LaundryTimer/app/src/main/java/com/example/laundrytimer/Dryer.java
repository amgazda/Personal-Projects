package com.example.laundrytimer;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.TextView;

public class Dryer extends Machine {

    transient static final long TIMESTART = 3600000L;
    public Dryer(String numberP, Context c, Button b) {super(numberP,c,b,"Dryer: ", TIMESTART, false); }
    public Dryer(String numberP, Context c, Button b, long timeRem, boolean bb) {
        super(numberP,c,b,"Dryer: ", timeRem, bb);
    }

}
