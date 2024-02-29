package days;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 2670
 *
 * @author gzq44
 * @date 2024/01/31 21:49
 **/
public class P2670 {

    public static void main(String[] args) {
        new P2670().distinctDifferenceArray(new int[]{3,2,3,4,2});
    }
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) diff++;
            map.merge(nums[i], 1, Integer::sum);
        }
        HashSet<Integer> set = new HashSet<>();
        int cnt = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (!set.contains(nums[i])) cnt++;
            set.add(nums[i]);
            Integer cur = map.get(nums[i]);
            if (cur == 1) {
                diff--;
            }
            ans[i] = cnt - diff;
            map.merge(nums[i], -1, Integer::sum);
        }
        return ans;
    }
}
