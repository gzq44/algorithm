package dp.num;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 233
 *
 * @author gzq44
 * @date 2024/01/12 23:23
 **/
public class P233 {


    public static void main(String[] args) {
        new P233().countDigitOne(13);
    }

    char[] s;
    int[][] dp;
    public int countDigitOne(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        dp = new int[s.length][2];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return dfs(0, 0, true);
    }
    int dfs(int i, int cnt, boolean isLimit) {
        if (i == s.length) return cnt;
        if (!isLimit && dp[i][cnt] >= 0) return dp[i][cnt];
        int limit = isLimit ? s[i] - '0' : 9;
        int res = 0;
        for (int j = 0; j <= limit; j++) {
            res += dfs(i + 1, cnt + (s[j] == '1' ? 1 : 0), isLimit && j == limit);
        }
        if (!isLimit) dp[i][cnt] = res;
        return res;
    }


//    public int countDigitOne(int n) {
//        s = String.valueOf(n).toCharArray();
//        int m = s.length;
//        dp = new int[m][m];
//        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
//        return dfs(0, 0, true, false);
//    }
//
//    int dfs(int i, int cnt, boolean isLimit, boolean isNum) {
//        int n = s.length;
//        if (i == n) return 0;
//        if (!isLimit && dp[i][cnt] >= 0) return dp[i][cnt];
//        int limit = isLimit ? (s[i] - '0') : 9;
//        int res = 0;
//        for (int j = 0; j < limit; j++) {
//            res += dfs(i + 1, cnt + (s[j] == '1' ? 1 : 0), isLimit && j == limit, false);
//        }
//        if (!isLimit) dp[i][cnt] = res;
//        return res;
//    }

}
