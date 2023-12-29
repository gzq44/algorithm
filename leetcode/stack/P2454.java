package stack;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 2454
 *
 * @author gzq44
 * @date 2023/12/26 22:53
 **/
public class P2454 {
    public static void main(String[] args) {
        new P2454().secondGreaterElement(new int[]{11,13,15,12,0,15,12,11,9});
    }
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        ArrayDeque<Integer> d1 = new ArrayDeque<>();
        ArrayDeque<Integer> d2 = new ArrayDeque<>();
        //如果不poll则 t 总会在stack中如果poll仅仅是条件比大即可又会把 t 给弹出去
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!d2.isEmpty() && nums[d2.peek()] < x) {
                Integer poll = d2.poll();
                ans[poll] = x;
            }
            while (!d1.isEmpty() && nums[d1.peek()] < x) {
                d2.push(d1.poll());
            }
            d1.push(i);
        }
        return ans;
    }


}
