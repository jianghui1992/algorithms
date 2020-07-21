package com.jh.linkedlist;

/**
 * 反转链表
 * Created by: edgewalk
 * 2020-06-22 20:33
 */
public class ReverseList {
    //反转单向链表
    public static Node reverseLinkedList(Node head) {
        Node pre = null; //记录一下当前节点的前一个节点
        Node next = null; //记录一下当前节点的后一个节点,方便head往下跳
        //1->2->3->null
        // null <- 1  2 -> 3 ->  null
        // null <- 1
        while (head != null) {
            next = head.next; //next =2
            head.next = pre; //head.next = null
            pre = head;  //pre=1
            head = next; // head = 2
        }
        return pre;
    }

    //反转双向链表
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null; //记录一下当前节点的前一个节点
        DoubleNode next = null; //记录一下当前节点的后一个节点
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static class Node<E> {
        public E data;
        public Node<E> next;
    }

    public static class DoubleNode<E> {
        public E data;
        public DoubleNode<E> prev;
        public DoubleNode<E> next;
    }
}
