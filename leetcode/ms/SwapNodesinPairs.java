package ms;

import utils.ListNode;

/**
 * 24
 *
 * @author gzq44
 * @date 2023/07/30 16:59
 **/
public class SwapNodesinPairs {


    public static void main(String[] args) {
        new SwapNodesinPairs().swapPairs(ListNode.createList(new Integer[]{1,2,3,4}));
    }
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode f1 = head;
        ListNode f2 = head.next;
        ListNode p = null;
        ListNode f = head.next;
        while (f1 != null && f2 != null) {
            f1.next = f2.next;
            f2.next = f1;
            if (p != null) p.next = f2;
            p = f1;
            f1 = f1.next;
            if (f1 != null) f2 = f1.next;
        }
        return f;
    }
}
