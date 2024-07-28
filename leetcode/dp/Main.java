package dp;

import java.util.Arrays;
import java.util.List;

/**
 * 动态规划题集
 *
 * @author gzq44
 * @date 2024/07/27 17:26
 **/
public class Main {

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
//    public static void main(String[] args) {
//        new Main().maxHeight(new int[][]{{7,11,17},{7,17,11},{11,7,17},{11,17,7},{17,7,11},{17,11,7}});
//    }
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
     *
     *      https://leetcode.cn/problems/make-array-strictly-increasing/description/
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        return 0;
    }

    private int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (g.get(mid) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }
}
