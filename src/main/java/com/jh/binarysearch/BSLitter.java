package com.jh.binarysearch;

/**
 * Created by: edgewalk
 * 2020-06-21 16:32
 */
public class BSLitter {
    public static void main(String[] args) {
        int[]arr = {1,1,2,3,3,3,4,5,6,6,6};
        System.out.println(nearestIndex(arr,2));
    }
    public static int nearestIndex(int[] sortedArr, int value) {
        int L = 0;
        int R = sortedArr.length - 1;
        int index = -1; //记录最左的索引
        while (L < R) {
            // mid = (L+R) / 2;  L 10亿  R 18亿  //这时会溢出
            // mid = L + (R - L) / 2
            int mid = L + ((R - L) >> 1);
            if (sortedArr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }
}
