package ms;

import utils.ListNode;

/**
 * 142
 *
 * @author gzq44
 * @date 2023/08/01 23:10
 **/
public class LinkedListCycleII {


    public static void main(String[] args) {
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(0, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(3, n2);
        n4.next = n2;
        new LinkedListCycleII().detectCycle(n1);
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode f1 = head;
        ListNode f2 = head;
        while (true) {
            f1 = f1.next;
            if (f2 != null && f2.next != null) f2 = f2.next.next;
            else return null;
            if (f1.equals(f2)) {
                while (true) {
                    if (head.equals(f2)) return head;
                    else {
                        head = head.next;
                        f2 = f2.next.next;
                    }
                }
            }
        }
    }
}
