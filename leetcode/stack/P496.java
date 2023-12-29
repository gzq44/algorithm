package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 496
 *
 * @author gzq44
 * @date 2023/12/26 21:36
 **/
public class P496 {

    public static void main(String[] args) {
        new P496().nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        ArrayDeque<Integer> d = new ArrayDeque<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums1[i], i);
        }
        for (int i = m - 1; i >= 0; i--) {
            while (!d.isEmpty() && d.peek() < nums2[i]) d.poll();
            if (!d.isEmpty() && map.get(nums2[i]) != null) ans[map.get(nums2[i])] = d.peek();
            d.push(nums2[i]);
        }
        return ans;
    }
}
