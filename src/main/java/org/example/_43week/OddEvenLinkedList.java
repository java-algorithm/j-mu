package org.example._43week;

public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode listNode = oddEvenList(listNode1);
        System.out.println(listNode);
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode answer = new ListNode();

        ListNode odd = new ListNode();
        ListNode even = new ListNode();

        ListNode oddTail = odd;
        ListNode evenTail = even;

        ListNode current = head;
        for (int i = 1; current != null; i++) {
            ListNode next = current.next;
            if (i % 2 == 0) { // even
                evenTail.next = current;
                evenTail = current;
                evenTail.next = null;
            } else { // odd
                oddTail.next = current;
                oddTail = current;
                oddTail.next = null;
            }
            current = next;
        }

        answer = odd.next;
        oddTail.next = even.next;

        return answer;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
