import java.util.*;

/**
 * shit
 *
 * @author gzq44
 * @date 2023/08/08 21:04
 **/
public class LeetCodeMain {

    public static void main(String[] args) {
        new LeetCodeMain().countPaths(7, new int[][]{{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}});
    }

    public int countPaths(int n, int[][] roads) {
        int MOD = (int) (1e9 + 7);
        long[][] w = new long[n + 1][n + 1];
        for (long[] init : w) {
            Arrays.fill(init, Long.MAX_VALUE / 2);
        }
        boolean[] vis = new boolean[n + 1];
        for (int[] road : roads) {
            w[road[0]][road[1]] = road[2];
            w[road[1]][road[0]] = road[2];
        }
        long[] dist = new long[n + 1];
        // 起始先将所有的点标记为「未更新」和「距离为正无穷」
        Arrays.fill(vis, false);
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        // 只有起点最短距离为 0
        dist[0] = 0;
        int[] f = new int[n + 1];
        f[0] = 1;
        // 迭代 n 次
        for (int p = 0; p < n; p++) {
            // 每次找到「最短距离最小」且「未被更新」的点 t
            int t = -1;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && (t == -1 || dist[i] < dist[t])) {
                    t = i;
                }
            }
            // 标记点 t 为已更新
            vis[t] = true;
            // 用点 t 的「最小距离」更新其他点
            for (int i = 0; i < n; i++) {
                if (i == t) continue;
                if (dist[i] == dist[t] + w[t][i]) {
                    f[i] = (f[i] + f[t]) % MOD;
                } else if (dist[i] > dist[t] + w[t][i]) {
                    f[i] = f[t];
                    dist[i] = dist[t] + w[t][i];
                }
            }
        }
        return f[n - 1];
    }


}

