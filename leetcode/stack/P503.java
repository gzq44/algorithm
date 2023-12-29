package stack;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 503
 *
 * @author gzq44
 * @date 2023/12/26 22:21
 **/
public class P503 {

    public static void main(String[] args) {
        new P503().nextGreaterElements(new int[]{1,2,1});
    }
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        ArrayDeque<Integer> d = new ArrayDeque<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = n + n - 1; i >= 0; i--) {
            int j = i % n;
            while (!d.isEmpty() && nums[j] >= d.peek()) {
                d.poll();
            }
            if (!d.isEmpty() && d.peek() > nums[j]) ans[j] = d.peek();
            d.push(nums[j]);

        }
        return ans;
    }
}
