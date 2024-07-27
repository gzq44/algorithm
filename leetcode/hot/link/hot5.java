package hot.link;

import utils.ListNode;

/**
 * 92
 *
 * @author gzq44
 * @date 2024/03/01 23:08
 **/
public class hot5 {

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4,n5);
        ListNode n3 = new ListNode(3,n4);
        ListNode n2 = new ListNode(2,n3);
        ListNode n1 = new ListNode(1,n2);
        new hot5().reverseBetween(n1, 1, 5);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode node = new ListNode(0, head);
        head = node;
        ListNode ans = head;
        left++;right++;
        if (left == 1) right--;
        right -= left;

        while (left > 2) {
            left--;
            head = head.next;
        }
        ListNode le1 = head;
        ListNode pre = head;
        head = head.next;
        ListNode le2 = head;
        while (right >= 0) {
            right--;
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        le1.next = pre;
        le2.next = head;
        return ans.next;
    }
}
