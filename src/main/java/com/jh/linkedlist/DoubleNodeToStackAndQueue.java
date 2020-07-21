package com.jh.linkedlist;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by: edgewalk
 * 2020-06-22 21:22
 */
public class DoubleNodeToStackAndQueue {
    public static <E> boolean isEqual(E o1, E o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    //随机的弹出和放入
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    public static class Node<E> {
        public E value;
        public Node<E> prev;
        public Node<E> next;

        public Node(E data) {
            value = data;
        }
    }

    public static class DoubleNode<E> {
        Node<E> head;//记录链表的头节点
        Node<E> tail;//记录链表的尾节点

        //把当前元素插入到头部
        public void addFromHead(E value) {
            Node<E> cur = new Node<>(value);
            if (head == null) {//插入第一个元素
                head = cur;
                tail = cur;
            } else {
                cur.next = head; //构造指针
                head.prev = cur;
                head = cur;  //变换头节点位置
            }
        }

        //把当前元素插入到头部
        public void addFromBottom(E value) {
            Node<E> cur = new Node<>(value);
            if (tail == null) {//插入第一个元素
                head = cur;
                tail = cur;
            } else {//构建指针,变换尾节点位置
                cur.prev = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        //拿出头节点元素
        public E popFromHead() {
            if (head == null) {
                return null;
            }
            Node<E> cur = head;
            if (head == tail) {//只有一个元素
                head = null;
                tail = null;
            } else {
                head = head.next;//移动头节点位置
                head.prev = null; //把指针变成null
                cur.next = null;
            }
            return cur.value;
        }

        //拿出尾节点元素
        public E popFromBottom() {
            if (tail == null) {
                return null;
            }
            Node<E> cur = tail;
            if (head == tail) {//只有一个元素
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
                tail.prev = null;
            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyStack<E> {
        private DoubleNode<E> doubleNode;

        public MyStack() {
            doubleNode = new DoubleNode<E>();
        }

        public void push(E value) {
            doubleNode.addFromHead(value);
        }

        public E pop() {
            return doubleNode.popFromHead();
        }

        public boolean isEmpty() {
            return doubleNode.isEmpty();
        }
    }

    public static class MyQueue<E> {
        private DoubleNode<E> doubleNode;

        public MyQueue() {
            doubleNode = new DoubleNode<E>();
        }

        public void push(E value) {
            doubleNode.addFromBottom(value);
        }

        public E poll() {
            return doubleNode.popFromHead();
        }

        public boolean isEmpty() {
            return doubleNode.isEmpty();
        }
    }
}
