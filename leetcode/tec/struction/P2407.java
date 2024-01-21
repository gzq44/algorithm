package tec.struction;

import java.util.Arrays;

/**
 * 2407
 *
 * @author gzq44
 * @date 2024/01/10 23:48
 **/
public class P2407 {

    public static void main(String[] args) {
        new P2407().lengthOfLIS(new int[]{4, 2, 1, 4, 3, 4, 5, 8, 15}, 3);
    }

    int[] max;

    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        int mx = Arrays.stream(nums).max().getAsInt();
        max = new int[4 * mx];
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) modify(1, 1, mx, 1, 1);
            else {
                int m = query(1, 1, mx, Math.max(nums[i] - k, 1), nums[i] - 1) + 1;
                modify(1, 1, mx, nums[i], m);
            }
        }

        return max[1];
    }

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
        if (L <= l && r <= R) return max[o];
        int m = (l + r) / 2;
        int ans = 0;
        if (L <= m) ans = Math.max(ans, query(o * 2, l, m, L, R));
        if (R > m) ans = Math.max(ans, query(o * 2 + 1, m + 1, r, L, R));
        return ans;
    }

/*
    int[] max;

    public int lengthOfLIS(int[] nums, int k) {
        int u = 0;
        for (int x : nums) u = Math.max(u, x);
        max = new int[u * 4];
        for (int x : nums) {
            if (x == 1) modify(1, 1, u, 1, 1);
            else {
                int res = 1 + query(1, 1, u, Math.max(x - k, 1), x - 1);
                modify(1, 1, u, x, res);
            }
        }
        return max[1];
    }

    private void modify(int o, int l, int r, int idx, int val) {
        if (l == r) {
            max[o] = val;
            return;
        }
        int m = (l + r) / 2;
        if (idx <= m) modify(o * 2, l, m, idx, val);
        else modify(o * 2 + 1, m + 1, r, idx, val);
        max[o] = Math.max(max[o * 2], max[o * 2 + 1]);
    }

    // 返回区间 [L,R] 内的最大值
    private int query(int o, int l, int r, int L, int R) { // L 和 R 在整个递归过程中均不变，将其大写，视作常量
        if (L <= l && r <= R) return max[o];
        int res = 0;
        int m = (l + r) / 2;
        if (L <= m) res = query(o * 2, l, m, L, R);
        if (R > m) res = Math.max(res, query(o * 2 + 1, m + 1, r, L, R));
        return res;
    }
*/
}
