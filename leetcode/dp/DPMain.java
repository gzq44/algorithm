package dp;

import java.util.*;

/**
 * 动态规划题集
 *
 * @author gzq44
 * @date 2024/07/27 17:26
 **/
public class DPMain {

    /**
     *
     *      1691. 堆叠长方体的最大高度
     *          给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
     *      如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
     *      返回 堆叠长方体 cuboids 可以得到的 最大高度 。
     *
     *      类似最长上升子序列
     *      1、当前长方体有三种排放方式，可从上个以j结尾的长方体转移来，对应的也是三种摆放，3 * 3 = 9
     *      2、i, j, t 表示 以 i 结尾的的第 t 种方式最大高度
     *      实现比较复杂 -> 没看仔细题目 ( widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj )
     *
     *      answer:
     *      排序 + LIS
     *      先排序二维cuboids
     *      在根据x、y、z排序
     *      why ?
     *      * widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj *
     *
     *      https://leetcode.cn/problems/maximum-height-by-stacking-cuboids/description/
     *
     */
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else if (a[1] != b[1]) {
                return b[1] - a[1];
            } else {
                return b[2] - a[2];
            }
        });

        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = cuboids[i][2];
        }
        for (int i = 0; i < n; i++) {
            int[] c1 = cuboids[i];
            for (int j = 0; j < i; j++) {
                int[] c2 = cuboids[j];
                if (c1[0] <= c2[0] && c1[1] <= c2[1] && c1[2] <= c2[2]) {
                    f[i] = Math.max(f[i], f[j] + c1[2]);
                }
            }
        }
        int ans = 0;
        for (int v : f) {
            ans = Math.max(ans, v);
        }
        return ans;
    }


    /**
     *      2407. 最长递增子序列 II
     *      给你一个整数数组 nums 和一个整数 k 。
     *      找到 nums 中满足以下要求的最长子序列：
     *          1、子序列 严格递增
     *          2、子序列中相邻元素的差值 不超过 k 。
     *      请你返回满足上述要求的 最长子序列 的长度。
     *      子序列 是从一个数组中删除部分元素后，剩余元素不改变顺序得到的数组。
     *
     *      输入：nums = [4,2,1,4,3,4,5,8,15], k = 3
     *      输出：5
     *
     *      4
     *      2
     *      1
     *      1、4
     *      1、3
     *      1、3、4、5、8、15
     *      dp + binary search 但是相比 300 这里多了k的限制，不能直接更新集合内元素，具有后效性
     *
     *      i,j 前 i 个 以 j 结尾的最大 需要遍历范围 j - k <= j' 取 max + 1 然后更新 j 的最大值 --> segment tree
     *
     *      https://leetcode.cn/problems/longest-increasing-subsequence-ii/description/
     */
    int[] f;
    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        f = new int[4 * max];
        for (int num : nums) {
            if (num == 1) {
                update(1, 1, max, 1, 1);
            } else {
                int mx = query(Math.max(num - k, 1), num - 1, 1, 1, max);
                update(1, 1, max, mx + 1, num);
            }
        }
        return f[1];
    }

    //单点修改
    private void update(int o, int l, int r, int d, int idx) {
        if (l == r) {
            f[o] = d;
            return;
        }
        int m = l + ((r - l) >> 1);
        if (m >= idx) {
            update(o * 2, l, m, d, idx);
        } else {
            update(o * 2 + 1, m + 1, r, d, idx);
        }
        f[o] = Math.max(f[o * 2], f[o * 2 + 1]);
    }

    private int query(int l, int r, int o, int ll, int rr) {
        if (l <= ll && rr <= r) return f[o];
        int m = ll + ((rr - ll) >> 1);
        int ans = 0;
        if (l <= m) ans = Math.max(ans, query(l, r, o << 1, ll, m));
        if (r > m) ans = Math.max(ans, query(l, r, o << 1 | 1, m + 1, rr));
        return ans;
    }


    /**
     *
     *      1187. 使数组严格递增
     *      选或不选
     *      https://leetcode.cn/problems/make-array-strictly-increasing/description/
     */
    HashMap<Integer, Integer> memo1[];
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        memo1 = new HashMap[arr1.length];
        Arrays.setAll(memo1, e -> new HashMap<>());
        int res = dfs1(0, -1, arr1, arr2);
        return res == Integer.MAX_VALUE / 2 ? -1 : res;
    }

    int dfs1(int i, int pre, int[] arr1, int[] arr2) {
        if (i >= arr1.length) {
            return 0;
        }
        if (memo1[i].containsKey(pre)) return memo1[i].get(pre);

        int mn = Integer.MAX_VALUE / 2;

        if (pre < arr1[i]) {
            mn = Math.min(mn, dfs1(i + 1, arr1[i], arr1, arr2));
        }

        int b = lowerBound(arr2, pre + 1);
        if (b != arr2.length) {
            mn = Math.min(mn, dfs1(i + 1, arr2[b], arr1, arr2) + 1);
        }

        memo1[i].put(pre, mn);
        return mn;
    }

    private int lowerBound(int[] g, int target) {
        int left = 0, right = g.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (g[mid] < target) {
                left = mid + 1; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return left; // 或者 left+1
    }

    public static void main(String[] args) {
        new DPMain().minOperations(new int[]{6,4,8,1,3,2}, new int[]{4,7,6,2,3,8,6,1});
    }
    /**
     *  https://leetcode.cn/problems/minimum-operations-to-make-a-subsequence/description/
     *  target 元素均不同
     *  类似定义为target中的上升序列
     *
     * @param target
     * @param arr
     * @return
     */
    public int minOperations(int[] target, int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();

        int m = target.length;
        for (int i = 0; i < m; i++) {
            map.put(target[i], i);
        }
        List<Integer> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int v = arr[i];
            if (!map.containsKey(v)) continue;
            int idx = lowerBound(g, map.get(v), map);
            if (idx == g.size()) {
                g.add(v);
            } else {
                g.set(idx, v);
            }
        }
        return m - g.size();
    }

    private int lowerBound(List<Integer> g, int target, Map<Integer, Integer> map) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (map.get(g.get(mid)) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }

    /*
    *
    *   五、状态机 DP
    *
    *   一般定义 f[i][j] 表示前缀a[:i] 在状态 j 下的最优值。一般 j 都很小。代表题目是「买卖股票」系列。
    *   https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
    *   https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
    *   https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii
    *   https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv
    *
    * */

    /**
     *
     *      188. 买卖股票的最佳时机 IV
     *
     *      https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv
     */
    int[] prices;
    int[][] memo;
    public int maxProfit(int k, int[] _prices) {
        int n = _prices.length;
        prices = _prices;
        memo = new int[n][k];
        for (int[] m : memo) Arrays.fill(m, -1);
        return dfs(n - 1, k, 0);
    }

    int dfs(int i, int j, int h) {
        if (i < 0) return Integer.MIN_VALUE;
        if (memo[i][j] != -1) return memo[i][j];

        if (h == 1) return memo[i][j] = Math.max(dfs(i - 1, j, h), dfs(i - 1, j - 1, 0) + prices[i]);
        return memo[i][j] = Math.max(dfs(i - 1, j, h), dfs(i - 1, j, 1) - prices[i]);
    }


    /**
     *
     *  https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-ii/
     *
     * @param zero
     * @param one
     * @param limit
     * @return
     */
    public int numberOfStableArrays(int zero, int one, int limit) {

        int MOD = (int)1e9 + 7;
        int[][][] f = new int[zero + 1][one + 1][limit];
        for (int i = 0; i <= Math.min(limit, zero); i++) {
            f[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(limit, one); j++) {
            f[0][j][1] = 1;
        }
        for (int i = 0; i < zero; i++) {
            for (int j = 0; j < one; j++) {
                f[i + 1][j + 1][0] = (int)(((long)f[i][j + 1][0] + f[i + 1][j][1] + (MOD - (limit < i ? f[i - 1 - limit][j + 1][1] : 0))) % MOD);
                f[i + 1][j + 1][1] = (int)(((long)f[i][j + 1][0] + f[i + 1][j][1] + (MOD - (limit < j ? f[i + 1][j - 1 - limit][0] : 0))) % MOD);
            }
        }
        return f[zero][one][0] + f[zero][one][1];
    }


}
