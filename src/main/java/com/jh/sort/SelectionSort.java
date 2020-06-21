package com.jh.sort;


import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {
    public static void main(String[] args) {
        //int[] arr = {30, 40, 10, 20};
        //selectSort(arr);
        //System.out.println(Arrays.toString(arr));
        //System.out.println(Arrays.toString(generateRandomArray(50, 50)));

        //对数器
        int testTimes = 10000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(succeed ? "success!" : "error!!!!!!!!!!!!!");
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        /*
        比较逻辑:
        第一次: 比较0 - N-1,最小值放0
        第二次: 比较1 - N-1, 最小值放1
        第三次: 比较2 - N-1, 最小值放2

        example:
        index:  0   1   2   3
        value: 30   40  10  20

        第一次:  i =0  minIndex =0
                arr[1] < arr[0]  ×   minIndex=0
                arr[2] < arr[0]  √   minIndex=2
                arr[3] < arr[2]  ×   minIndex=2
                swap(arr,0,2)
                 index:  0   1   2   3
                 value: 10      30
        index:  0   1   2   3
        value: 10   40  30  20

         第二次: i=1  minIndex =1
                arr[2] < arr[1]  √   minIndex=2
                arr[3] < arr[1]  ×   minIndex=3
                swap(arr,1,3)
                 index:  0   1   2   3
                 value:     20      40
         index:  0   1   2   3
         value: 10   20  30  40

         第三次: i=2  minIndex =2
                arr[3] < arr[2]  ×   minIndex=2
                swap(arr,2,2)
         */
        for (int i = 0; i < arr.length; i++) {// i ~ N-1
            int minIndex = i; //记录最小值的下标
            for (int j = i + 1; j < arr.length; j++) {//i ~ N-1 找最小值的下标
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    //for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //Math.random()  [0,1) 小数
        //Math.random()* N  [0,N)  小数
        //(int) ( Math.random()* (N+1) )  [0,N]  整数
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            // - 的目的是为了出现负数 让 arr[i] 属于 [-maxSize,maxSize}
            arr[i] = (int) (Math.random() * (maxSize + 1)) - (int) (Math.random() * (maxSize + 1));
        }
        return arr;
    }

    //for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            res[i] = arr[i];
//        }
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


}
