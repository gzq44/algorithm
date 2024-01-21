package dp.num;

import java.util.Arrays;

/**
 * 600
 * 二进制数位
 * @author gzq44
 * @date 2024/01/13 15:30
 **/
public class P600 {


    char[] s;
    int[][] dp;
    public int findIntegers(int m) {
        s = Integer.toBinaryString(m).toCharArray();
        dp = new int[m][2];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        return dfs(0, 0, true);
    }

    int dfs(int i, int p, boolean isLimit) {
        if (i == s.length) return 1;
        if (!isLimit && dp[i][p] >= 0) return dp[i][p];
        int up = isLimit ? s[i] - '0' : 1;
        int res = dfs(i + 1, 0, isLimit && up == 0);
        if (p != 1 && up == 1) res += dfs(i + 1, 1, isLimit);
        if (!isLimit) dp[i][p] = res;
        return res;
    }
}
