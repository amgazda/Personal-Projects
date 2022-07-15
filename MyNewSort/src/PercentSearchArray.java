public class PercentSearchArray {
    public int search(int[] arr, int target) {
        int min = arr[0];
        if(min == 0) {
            if(target == 0) {
                return 0;
            } else {
                return -1;
            }
        }
        int max = arr[arr.length-1];
        int size = arr.length;
        int mid = 0;
        while(arr[mid] != target) {
            double maxPercent = (max-target)/max;
            double minPercent = (target-min)/min;
            if (maxPercent < minPercent) {

            } else if (minPercent < maxPercent) {

            } else { //equal

            }
        }
        return mid;
    }
}
