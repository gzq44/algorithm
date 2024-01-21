package tec.struction;

import java.util.Arrays;

/**
 * 300
 *
 * @author gzq44
 * @date 2024/01/14 00:04
 **/
public class P300 {

    public static void main(String[] args) {
        new P300().lengthOfLIS(new int[]{-10000});
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int mx = Arrays.stream(nums).max().getAsInt() + 10002;
        max = new int[4 * mx];
        for (int i = 0; i < n; i++) {
            int x= nums[i] + 10001;
            int m = query(1, 1, mx, 1, x - 1) + 1;
            modify(1, 1, mx, x, m);
        }

        return max[1];
    }

    int[] max;


    private void modify(int o, int l, int r, int idx, int val) {
        if (l == r) {
            max[o] = val;
            return;
        }
        int m = (l + r) / 2;
        if (m < idx) modify(o * 2 + 1, m + 1, r, idx, val);
        else modify(o * 2, l, m, idx, val);
        max[o] = Math.max(max[o * 2], max[o * 2 + 1]);
    }

    private int query(int o, int l, int r, int L, int R) {
//        if (R < l || L > r) return 0;
        if (L <= l && r <= R) return max[o];
        int m = (l + r) / 2;
        int ans = 0;
        if (L <= m) ans = Math.max(ans, query(o * 2, l, m, L, R));
        if (R > m) ans = Math.max(ans, query(o * 2 + 1, m + 1, r, L, R));
        return ans;
    }
}
