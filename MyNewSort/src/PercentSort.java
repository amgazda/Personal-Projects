import java.util.ArrayList;

public class PercentSort {
    private ArrayList<Integer> arr;

    public PercentSort() {
        arr = new ArrayList<>();
    }

    public void add(int a) {
        //add check for a == 0
        int max = arr.get(arr.size()-1);
        int min = arr.get(0);
        int size = arr.size();
        double rmax = (max-a)/max;
        double rmin = (a-min)/a;
        int mid;
        if (rmax < rmin) {
            mid = (int) Math.ceil(rmax*size);
            if (mid>(size-1)) {mid = size-1;}
        } else {
            mid = (int) Math.floor(rmin*size);
            if (mid < 0) {mid = 0;}
        }
    }
}
//test