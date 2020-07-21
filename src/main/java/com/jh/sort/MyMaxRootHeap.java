package com.jh.sort;

/**
 * 大根堆实现
 * Created by: edgewalk
 * 2020-07-21 23:19
 */
public class MyMaxRootHeap {
    private final int limit; //数组的大小
    private final int[] heap;  //存储数据的数组
    private int heapSize; //当前组织成堆结构的数据的个数/新加的数放置的位置

    public MyMaxRootHeap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    public void push(int value) {
        if (heapSize == limit) throw new RuntimeException("heap is full");
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);//传递到方法内部的值还是heapSize
    }

    private void heapInsert(int[] arr, int index) {
        //arr[index]如果一直比arr[index父节点]大,index和index父节点交换
        // (0-1)/2=0 ,直到看到最顶元素是退出
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //数组元素交换
    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        MyMaxRootHeap my = new MyMaxRootHeap(100);
        my.push(4);
    }
}
