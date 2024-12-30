package org.example._43week;

public class FindTheHighestAltitude {

    public static void main(String[] args) {}

    public int largestAltitude(int[] gain) {
        int max = Math.max(0,gain[0]);

        for(int i=1; i<gain.length; i++){
            gain[i] = gain[i] + gain[i-1];
            max = Math.max(gain[i],max);
        }

        return max;
    }
}
