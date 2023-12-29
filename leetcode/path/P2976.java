package path;

import java.util.Arrays;

/**
 * 100156
 * https://leetcode.cn/problems/minimum-cost-to-convert-string-i/description/
 *
 * @author gzq44
 * @date 2023/12/24 21:04
 **/
public class P2976 {
    public static void main(String[] args) {
        new P2976().minimumCost("abcd", "acbe", new char[]{'a','b','c','c','e','d'}, new char[]{'b','c','b','e','b','e'}, new int[]{2,5,5,1,2,20});
    }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        for (long[] i : w) {
            Arrays.fill(i, Long.MAX_VALUE);
        }
        for (int i = 0; i < 26; i++) {
            w[i][i] = 0;
        }
        floyd(original, changed, cost);
        int n = source.length();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';
            if (s != t) {
                if (w[s][t] == Long.MAX_VALUE) return -1;
                ans += w[s][t];
            }
        }
        return ans;
    }

    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    long[][] w = new long[26][26];
    void floyd(char[] original, char[] changed, int[] cost) {
        int n = original.length;
        // floyd 基本流程为三层循环：
        // 枚举中转点 - 枚举起点 - 枚举终点 - 松弛操作
        for (int p = 0; p < n; p++) {
            int b1 = original[p] - 'a';
            int b2 = changed[p] - 'a';
            int v = cost[p];
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (w[i][b1] != Long.MAX_VALUE && w[b2][j] != Long.MAX_VALUE) w[i][j] = Math.min(w[i][j], w[i][b1] + w[b2][j] + v);
                }
            }
        }
    }
}
