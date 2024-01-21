package tec.struction;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2569
 * 1, n 中1的个数
 *
 * @author gzq44
 * @date 2024/01/14 10:12
 **/
public class P2569 {

    public static void main(String[] args) {
        new P2569().handleQuery(new int[]{0,0,1,0,1,1,0,0,0,1,0,1},
                new int[]{4,33,48,7,25,13,13,6,29,23,6,9},
                new int[][]{{1,5,10},{1,4,6},{2,23,0},{3,0,0}});
    }

    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        ArrayList<Long> res = new ArrayList<>();
        int n = queries.length;
        int N = nums1.length;
        build(nums1, 1, 1, N);
        long sum = 0;
        for (int num : nums2) {
            sum += num;
        }
        for (int[] query : queries) {
            int x = query[0];
            int l = query[1];
            int r = query[2];
            if (x == 1) {
                modify(1, 1, N, l + 1, r + 1);
            } else if (x == 2) {
                long q = tree[1];
                sum += (long) l * q;
            } else {
                res.add(sum);
            }
        }
        long[] ans = new long[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    long[] tree = new long[100001 * 4];
    boolean[] mark = new boolean[100001 * 4];

    private void build(int[] nums1, int p, int l, int r) {
        if (l == r) {
            tree[p] = nums1[l - 1];
            return;
        }
        int m = l + ((r - l) >> 1);
        build(nums1, p << 1, l, m);
        build(nums1, p << 1 | 1, m + 1, r);
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }

    private void pushDown(int p, int l, int r) {
        if (!mark[p]) return;
        int m = l + ((r - l) >> 1);
        tree[p << 1] = m - l + 1 - tree[p << 1];
        mark[p << 1] = !mark[p << 1];
        tree[p << 1 | 1] = r - m - tree[p << 1 | 1];
        mark[p << 1 | 1] = !mark[p << 1 | 1];
        mark[p] = !mark[p];
    }

    private void modify(int o, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            tree[o] = r - l + 1 - tree[o];
            mark[o] = !mark[o];
            return;
        }
        pushDown(o, l, r);
        int m = l + ((r - l) >> 1);
        if (L <= m) modify(o << 1, l, m, L, R);
        if (R > m) modify(o << 1 | 1, m + 1, r, L, R);
        tree[o] = tree[o << 1] + tree[o << 1 | 1];
    }

}
