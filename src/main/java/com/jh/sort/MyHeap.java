package com.jh.sort;

/**
 * 大根堆实现
 * Created by: edgewalk
 * 2020-07-21 23:19F
 */
public class MyHeap {
    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");

                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

    public static class MyMaxHeap {
        private final int limit; //数组的大小
        private final int[] heap;  //存储数据的数组
        private int heapSize; //当前组织成堆结构的数据的个数/新加的数放置的位置

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        //放入一个元素,同时数组依然保持大根堆构造
        public void push(int value) {
            if (heapSize == limit) throw new RuntimeException("heap is full");
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);//传递到方法内部的值还是heapSize
        }

        /**
         * 默认把当前元素放入到数组的最后,也就是堆的最尾层
         * 同时改元素会一直和它的父节点比较,如果比父节点大,则交换,直到比父节点小或者比到了最顶元素退出
         */
        private void heapInsert(int[] arr, int index) {
            //arr[index]如果一直比arr[index父节点]大,index和index父节点交换
            // (0-1)/2=0 ,直到看到最顶元素是退出
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //弹出最大值,同时剩余的元素依然组成大跟堆
        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize); //把最后一个元素放0位置,同时heapSize-1
            heapify(heap, 0, heapSize);//第0个元素做heapify
            return ans;
        }

        /**
         * 当前元素会和左右子元素的最大值比较,比如当前元素比最大子元素小,则交换, 直到比最大子元素大或者没有子节点
         */
        private void heapify(int[] arr, int index, int heapSize) {
            // 左孩子: 2* index+1
            // 右孩子: 2 *index+2
            // 父节点: (index-1)/2
            int left = 2 * index + 1;
            while (left < heapSize) {
                int max = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;//找到左右孩子中最大值的索引
                if (arr[index] >= arr[max]) break;
                swap(arr, index, max);
                index = max;
                left = 2 * index + 1;
            }
        }


        //数组元素交换
        private void swap(int[] arr, int x, int y) {
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }
    }

    public static class RightMaxHeap {
        private final int limit;
        private final int[] arr;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

}
