package com.jh.linkedlist;

/**
 * Created by: edgewalk
 * 2020-06-22 20:48
 */
public class DeleteGivenValue {

    public static <E> Node<E> removeValue(Node<E> head, E value) {
        //删除头上的value
        //3->3->3->1->7->5->3->3->2->null  ==>  1->7->5->3->3->2->null
        while (head != null) {
            if (!head.equals(value)) {
                break;
            }
            head = head.next;
        }
        //删除中间的value
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.equals(value)) {
                pre.next = cur.next; //让pre跳向下一个节点
            } else {
                pre = cur;
            }
        }
        return head;
    }
}
