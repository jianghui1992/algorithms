package com.jh.binarysearch;

/**
 * Created by: edgewalk
 * 2020-06-21 16:00
 */
public class BSExist {

    public static void main(String[] args) {
        int[] sortedArr = {1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 888};
        System.out.println(exist(sortedArr, 888));
    }

    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) return false;
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) {
            // mid = (L+R) / 2;  L 10亿  R 18亿  //这时会溢出
            // mid = L + (R - L) / 2
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        //当跳出循环,说明L大于等于R,此时直接判断L相不相等就行
        return sortedArr[L] == num;
    }
}
