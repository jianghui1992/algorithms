package com.jh.sort;

import java.util.Arrays;


/**
 * 堆排序
 * Created by: edgewalk
 * 2020-07-20 22:07
 */
public class HeapSort {
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
        // 默认小根堆
//        PriorityQueue<Integer> heap = new PriorityQueue<>();
//        heap.add(6);
//        heap.add(8);
//        heap.add(0);
//        heap.add(2);
//        heap.add(9);
//        heap.add(1);
//
//        while (!heap.isEmpty()) {
//            System.out.println(heap.poll());
//        }

        int testTime = 100000;
        int maxSize = 1000;
        int maxValue = 1000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

    /**
     * 堆排序
     * 1 把数组组织成大根堆
     * 2 将第0个元素(最大值)和最后一个元素交换,然后heapSize-1,然后剩余元素heapify重新组织成大根堆,
     * 3 重复第二步直到heapSize=0(当最大值往最后放的时候,堆大小就会减小一个)
     */
    public static void heapSort(int[] arr) {

        //1 把数据组织成大根堆

        //时间复杂度:O(N*logN)
//        for (int i = 0; i < arr.length; i++) {  //O(N)
//            heapInsert(arr, i); //O(logN)
//        }

        //优化点: O(N)
        //从后忘前遍历:相当于从最底层元素开始heapify
        //由于最底层元素没有子元素,所以相当于最底层元素基本不用操作
        //而当最底层元素排满的情况下,占整个堆的1/2
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //2 将第0个元素(最大值)和最后一个元素交换,然后heapSize-1,然后剩余元素heapify重新组织成大根堆,
        //3 重复第二步直到heapSize=0(当最大值往最后放的时候,堆大小就会减小一个)
        int heapSize = arr.length;
        //O(N* logN)
        while (heapSize > 0) { //O(N)
            swap(arr, 0, --heapSize); //O(1)
            heapify(arr, 0, heapSize);  //O(logN)
        }
    }

    /**
     * 当前元素会和左右子元素的最大值比较,比如当前元素比最大子元素小,则交换, 直到比最大子元素大或者没有子节点
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        // 左孩子: 2* index+1
        // 右孩子: 2 *index+2
        // 父节点: (index-1)/2
        int left = index * 2 + 1;
        while (left < heapSize) { //肯定存在孩子节点(至少存在一个左孩子)
            int max = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;//找到左右孩子中最大值的索引
            if (arr[index] >= arr[max]) break;
            //走到此处,说明 当前元素比最大子孩子小,则交换
            swap(arr, index, max);//交换
            index = max;//把交换后的位置赋值给index
            left = index * 2 + 1;//重新计算index的左孩子的坐标值
        }
    }


    /**
     * 默认把当前元素放入到数组的最后,也就是堆的最尾层
     * 同时改元素会一直和它的父节点比较,如果比父节点大,则交换,直到比父节点小或者比到了最顶元素退出
     */
    private static void heapInsert(int[] arr, int index) {
        // 父节点: (index-1)/2
        //arr[index]如果一直比arr[index父节点]大,index和index父节点交换
        // (0-1)/2=0 ,直到看到最顶元素是退出
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}