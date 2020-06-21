package com.jh.binarysearch;

/**
 * Created by: edgewalk
 * 2020-06-21 20:03
 */
public class BS1 {
    public static void main(String[] args) {
        int[] arr = {4,2,3,2,4,5,6};
        System.out.println(getLessIndex(arr));
    }
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        // arr[0]                             arr[length-1]
        //       arr[1]       arr[length-2]
        //               ...
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                //则说明左半,存在局部最小
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                //这说明右半存在 局部追最小
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
