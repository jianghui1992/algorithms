package com.jh.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {30, 40, 10, 20, 60, 50};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        /*
        第一步: 让 0-1有序  1位置和前面的比,比前面小就交换位置
            30, 40, 10, 20, 60, 50
        第二步: 让0-2有序  2位置依次和前面的数比,比前面数小就交换位置
            30, 40, 10, 20, 60, 50
            30, 10, 40, 20, 60, 50
            10, 30, 40, 20, 60, 50
        第三步: 让0-3有序 3位置依次和前面的数比,比前面数小就交换位置
            10, 30, 40, 20, 60, 50
            10, 30, 20, 40, 60, 50
            10, 20, 30, 40, 60, 50

         */
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
