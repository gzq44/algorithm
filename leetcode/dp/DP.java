package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * dp
 *
 * @author gzq44
 * @date 2023/08/11 11:16
 **/
public class DP {

    public static void main(String[] args) {
        new xx().longestIdealString("acfgbd", 2);
    }
}

class xx {
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[] f = new int[26];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = -k; j <= k; j++) {
                if (c - j >= 0 && c - j < 26) f[c] = Math.max(f[c], f[c - j] + 1);
                ans = Math.max(ans, f[c]);
            }
        }
        return ans;
    }
}

/**
 * https://leetcode.cn/problems/number-of-increasing-paths-in-a-grid/description/
 * 思路 ：转移的方向 - memo
 **/
class NumberofIncreasingPathsinaGrid {

    int MOD = (int) 1e9 + 7;
    int n;
    int m;
    int[][] grid;
    boolean[][] vis;

    public int countPaths(int[][] _grid) {
        grid = _grid;
        n = grid.length;
        m = grid[0].length;
        cache = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = (ans + dfs(i, j)) % MOD;
            }
        }
        return ans;
    }

    int[][] cache;
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int dfs(int i, int j) {
        if (cache[i][j] != -1) return cache[i][j];
        int res = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < n && y >= 0 && y < m && grid[i][j] > grid[x][y]) {
                res = (res + dfs(x, y)) % MOD;
            }
        }
        return cache[i][j] = res;
    }
}

class NumberofPeopleAwareofaSecret {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = (int) 1e9 + 7;
        int[] f = new int[n + 1];
        f[1] = 1;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = delay; i - j >= 1 && j < forget; j++) {
                f[i] = (f[i] + f[i - j]) % MOD;
            }
        }
        for (int i = 0; i < forget; i++) ans = (ans + f[n - i]) % MOD;
        return ans;
    }
}



