package com.jh.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortArrayDistanceLessK {

    /**
     * 当排好序的时候,则每个元素最大移动的位置为k, 即当k=5时,每个元素最远移动的位置为5
     * 能来到0位置的元素只可能是0-5的元素,则我们可以把0-5放入小跟堆,然后弹出一个(肯定是全局最小)放到0位置
     * 能来到1位置的元素只可能是1-6的元素,则我们可以把6位置元素放入小跟堆,然后弹出一个放到1位置
     */
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) return;

        //系统默认实现:小跟堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        //把0-k位置的元素放入堆中
        //防止 arr.length=3,k=5这种情况出现
        //egg.当 k=5时, 需要把0-5的元素放入到堆中
        for (; index <= Math.min(arr.length - 1, k); index++) {
            minHeap.add(arr[index]);
        }

        //i控制的是堆的最前面元素索引
        //index控制的是堆的最后元素索引
        int i = 0;
        for (; index < arr.length; i++, index++) {
            arr[i] = minHeap.poll();
            minHeap.add(arr[index]);
        }

        //arr.length不一定是5的倍数,所以最后堆还会剩余小于k的元素
        while (!minHeap.isEmpty()) {
            arr[i++] = minHeap.poll();
        }
    }

    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
