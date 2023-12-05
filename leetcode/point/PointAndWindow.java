package point;

/**
 * 指针和窗口
 *
 * @author gzq44
 * @date 2023/08/06 22:40
 **/
public class PointAndWindow {


}

/**
 * 2444. Count Subarrays With Fixed Bounds
 * https://leetcode.cn/problems/count-subarrays-with-fixed-bounds/description/
 *
 * @author gzq44
 * @date 2023/08/06 22:40
 **/
class CountSubarraysWithFixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int p = 0;
        int f1 = -1;
        int f2 = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxK || nums[i] < minK) {
                p = i;
            }
            if (nums[i] == minK) f1 = i;
            if (nums[i] == maxK) f2 = i;
            if (f1 >= p && f2 >= p) {
                ans += Math.min(f1, f2) - p;
            }
        }
        return ans;
    }
}



