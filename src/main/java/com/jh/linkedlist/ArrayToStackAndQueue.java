package com.jh.linkedlist;

/**
 * Created by: edgewalk
 * 2020-06-22 21:46
 */
public class ArrayToStackAndQueue {
    public static class MyQueue<E> {
        public int putIndex; //可以放元素的索引位置
        public int pollIndex;//可以拿元素的索引位置
        public int length;//数组长度
        public int size; //数组中实时元素个数
        public Object[] arr;

        public MyQueue(int length) {
            arr = new Object[length];
            putIndex = 0;
            pollIndex = 0;
            size = 0;
            this.length = length;
        }


        public void push(int value) {
            if (size == length) {
                throw new RuntimeException("队列满了");
            }
            size++;
            arr[putIndex] = value;
            putIndex = nextIndex(putIndex);
        }

        @SuppressWarnings("unchecked")
        public E poll() {
            if (size == 0) {
                throw new RuntimeException("队列空了");
            }
            size--;
            Object result = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            return (E) result;
        }


        public int nextIndex(int index) {
            return index < length - 1 ? index + 1 : 0;
        }
    }
}
