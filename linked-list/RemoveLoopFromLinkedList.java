/**
 * Definition for singly-linked list.
 * class ListNode {
 * public int val;
 * public ListNode next;
 * ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    public ListNode solve(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode tortoise = head;
        ListNode hare = head;

        /**
         * check for cycle
         */
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (hare == tortoise)
                break;
        }

        /**
         * if no cycle present, return head as it is
         */
        if (hare == null || hare.next == null)
            return head;

        /**
         * move tortoise to head and move hare and totoise one by one until both of them have same head
         */
        tortoise = head;
        while (tortoise.next != hare.next) {
            tortoise = tortoise.next;
            hare = hare.next;
        }
        hare.next = null;
        return head;
    }
}
