package com.jh.heap;


import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 比较器回顾
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        Student student1 = new Student("A", 2, 20);
        Student student2 = new Student("B", 3, 21);
        Student student3 = new Student("C", 1, 22);

        PriorityQueue<Student> minHeap = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //返回负数 o1.age < o2.age
                //返回正数 o1.age > o2.age
                //返回0  相等
                // 可以记忆为2个数相减,通过结果判断大小
                return o1.age - o2.age;
            }
        });
        minHeap.add(student1);
        minHeap.add(student2);
        minHeap.add(student3);


        student1.age = 100;

        while (!minHeap.isEmpty()) {
            Student student = minHeap.poll();
            System.out.println("name : " + student.name + ", id : " + student.id + ", age : " + student.age);
        }

    }

    @AllArgsConstructor
    public static class Student {
        public String name;
        public int id;
        public int age;
    }
}
