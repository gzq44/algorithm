import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图
 *
 * @author gzq44
 * @date 2024/08/12 22:10
 **/
public class GraphMain {

    public static void main(String[] args) {
        new GraphMain().findShortestCycle(5, new int[][]{{2,4},{0,1},{3,2},{4,0},{1,3}});
    }
    /**
     *
     * @param n
     * @param edges
     * @return
     */
    public int findShortestCycle(int n, int[][] edges) {
        List<Integer>[] path = new List[n];
        Arrays.setAll(path, o -> new ArrayList<>());
        for (int[] e : edges) {
            path[e[0]].add(e[1]);
            path[e[1]].add(e[0]);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int res = bfs(i, new boolean[n], path);
            if (res != -1) {
                ans = Math.min(ans, res);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    int bfs(int i, boolean[] vis, List<Integer>[] path) {
        ArrayDeque<int[]> d = new ArrayDeque<>();
        int[] cnt = new int[vis.length];
        int step = 1;
        d.push(new int[]{i, -1});
        vis[i] = true;
        int mn = Integer.MAX_VALUE;
        while (!d.isEmpty()) {
            int size = d.size();
            for (int j = 0; j < size; j++) {
                int[] poll = d.poll();
                int cur = poll[0];
                int pa = poll[1];
                for (Integer ni : path[cur]) {
                    if (pa == ni) continue;
                    if (!vis[ni]) {
                        d.offerLast(new int[]{ni, cur});
                        vis[ni] = true;
                        cnt[ni] = step;
                    } else {
                        mn = Math.min(mn, cnt[cur] + cnt[ni] + 1);
                    }
                }
            }
            step++;
        }
        return mn;
    }
}
