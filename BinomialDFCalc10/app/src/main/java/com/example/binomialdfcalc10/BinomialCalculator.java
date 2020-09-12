package com.example.binomialdfcalc10;

public class BinomialCalculator
{
    private int tries;
    private double prob;
    private int successes;
    public BinomialCalculator(int t, double p, int s)
    {
        tries=t;
        prob=p;
        successes=s;
    }
    public double binomPDF()
    {
        double pf=1-prob;
        int num = 1;
        for(int nfact=1;nfact<=tries;nfact++)
        {
            num *= nfact;
        }
        int xfact = 1;
        int nxfact = 1;
        for(int i=1;i<=successes;i++)
        {
            xfact *= i;
        }
        for(int j=1;j<=(tries-successes);j++)
        {
            nxfact *= j;
        }
        int denom = xfact*nxfact;
        double coeff = (double)num/denom;

        return coeff * Math.pow(prob, successes) * Math.pow(1-prob, tries-successes);
    }
}

