package com.example.laundrytimer;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.TextView;

public class Washer extends Machine {

    transient static final long TIMESTART = 1800000L;
    public Washer(String numberP, Context c, Button b) {
        super(numberP,c,b,"Washer: ", TIMESTART, false);
    }
    public Washer(String numberP, Context c, Button b, long timeRem, boolean bb) {
        super(numberP,c,b,"Washer: ", timeRem, bb);
    }

}
