package com.example.binomialdfcalc10;

public class GeometricCalculator {
    private double prob;
    private int trials;

    public GeometricCalculator(double p, int t){
        prob=p;
        trials=t;
    }

    public double et(){
        return Math.pow(1-prob,trials-1) * prob;
    }

    public double gt(){
        return Math.pow(1-prob,trials);
    }

    public double lt() {
        return 1 - (Math.pow(1-prob,trials-1) * prob) - Math.pow(1-prob,trials);
    }


}
