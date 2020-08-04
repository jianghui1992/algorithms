package com.jh.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 需求:
 * 对添加到堆内元素,修改值的时候,还需要是大跟堆
 */
public class AlterValueHeap {

    public static class MyMinHeap<T> {
        private ArrayList<T> heap;//使用arrayList作为动态数组
        private HashMap<T, Integer> indexMap;//记录添加的每个元素的在heap的索引位置
        private int heapSize;
        private Comparator<? super T> comparator;//比较器,比较添加的2个元素大小


        public MyMinHeap(Comparator<? super T> com) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            comparator = com;
        }

        public static void main(String[] args) {

            Student student1 = new Student("A", 2, 20);
            Student student2 = new Student("B", 3, 21);
            Student student3 = new Student("C", 1, 22);
            MyMinHeap<Student> minHeap = new MyMinHeap<>((o1, o2) -> o1.age - o2.age);

            minHeap.push(student1);
            minHeap.push(student2);
            minHeap.push(student3);
            student1.age = 100;
            minHeap.resign(student1);

            while (!minHeap.isEmpty()) {
                Student student = minHeap.pop();
                System.out.println("name : " + student.name + ", id : " + student.id + ", age : " + student.age);
            }

        }


        public void push(T value) {
            heap.add(value);
            indexMap.put(value, heapSize);
            heapInsert(heapSize++);
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public T pop() {
            T result = heap.get(0);
            swap(0, heapSize - 1);
            indexMap.remove(result);
            heap.remove(heapSize - 1);
            heapify(0, --heapSize);
            return result;
        }

        //当修改堆中某个元素的值的时候,重新变成大根堆
        public void resign(T value) {
            Integer valueIndex = indexMap.get(value);//获取当前元素的索引
            //二者只会命中一个
            heapInsert(valueIndex);
            heapify(valueIndex, heapSize);
        }

        //和父元素比较,如果小则交换
        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //和子元素中的最小值比较,如果大则交换
        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int max = left + 1 < heapSize &&
                        comparator.compare(heap.get(left), heap.get(left + 1)) > 0 ?
                        left + 1 : left;
                if (comparator.compare(heap.get(index), heap.get(max)) < 0) break;
                swap(index, max);
                index = max;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            T v1 = heap.get(i);
            T v2 = heap.get(j);
            heap.set(i, v2);
            heap.set(j, v1);
            //改变indexMap中的位置
            indexMap.put(v1, j);
            indexMap.put(v2, i);
        }
    }

    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }
}
