import java.util.Arrays;

/**
 * 381
 *
 * @author gzq44
 * @date 2024/01/21 11:08
 **/
public class W381 {
    //floyd
    public int[] countOfPairs(int n, int x, int y) {
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(edges[i], 100000);
            edges[i][i] = 0;
        }
        //init
        for (int i = 0; i < n - 1; i++) {
            edges[i][i + 1] = 1;
            edges[i + 1][i] = 1;
        }
        if (x != y) {
            edges[x][y] = 1;
            edges[y][x] = 1;
        }
        for (int p = 0; p <= n - 1; p++) {
            //从p到p + 1
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    edges[i][j] = Math.min(edges[i][j], edges[i][p] + edges[p][j]);
                }
            }
        }
        int[] ans = new int[n];
        for (int[] e : edges) {
            for (int i = 0; i < n; i++) {
                if (e[i] == 0) continue;
                ans[e[i] - 1]++;
            }
        }
        return ans;
    }

    public int minimumPushes(String word) {
        int n = word.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += cnt[25 - i] * ((i / 8) + 1);
        }
        return ans;
    }
}
