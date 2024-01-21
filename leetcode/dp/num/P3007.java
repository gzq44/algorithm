package dp.num;

import tec.struction.P300;

import java.util.Arrays;

/**
 * 3007
 *
 * @author gzq44
 * @date 2024/01/15 23:20
 **/
public class P3007 {

    public static void main(String[] args) {
        new P3007().findMaximumNumber(7, 2);
    }

    public long findMaximumNumber(long k, int _x) {
        x = _x;
        long left = 1;
        long right = (k + 1) * (1 << x);
        while (left < right) {
            long mid = left + (right - left) / 2;
            long res = countDigitOne(mid);
            if (res <= k)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }

    int x;
    char[] s;
    long[][] dp;

    private long countDigitOne(long num) {
        s = Long.toBinaryString(num).toCharArray();
        dp = new long[s.length][s.length];
        for (long[] a : dp) {
            Arrays.fill(a, -1);
        }
        return dfs(0, 0, true);
    }

    long dfs(int i, int cnt, boolean isLimit) {
        if (i == s.length) return cnt;
        if (!isLimit && dp[i][cnt] >= 0) return dp[i][cnt];
        int limit = isLimit ? s[i] - '0' : 1;
        long res = 0;
        for (int j = 0; j <= limit; j++) {
            res += dfs(i + 1, cnt + (((s.length - i) % x == 0 && j == 1) ? 1 : 0), isLimit && j == limit);
        }
        if (!isLimit) dp[i][cnt] = res;
        return res;
    }
}
