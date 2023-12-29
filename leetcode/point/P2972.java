package point;

/**
 *  10033
 *  https://leetcode.cn/problems/count-the-number-of-incremovable-subarrays-ii/description/
 *  答案会是a的前缀+后缀 如果删除中间某一段使a递增那么多删点a仍可以保持递增-贡献
 * @author gzq44
 * @date 2023/12/24 11:49
 **/
public class P2972 {
    public static void main(String[] args) {
        new P2972().incremovableSubarrayCount(new int[]{6,5,7,8});
    }

    public long incremovableSubarrayCount(int[] a) {
        int n = a.length;
        int ans = 0;
        int i = 0;
        while (i != n - 1 &&  a[i] < a[i + 1]) i++;
        if (i == n - 1) return (1 + n) * n / 2;
        ans += i + 2;
        for (int j = n - 1; j >= 0; j--) {
            while (i >= 0 && a[i] >= a[j]) i--;
            ans += i + 2;
        }
        return ans;
    }
}
