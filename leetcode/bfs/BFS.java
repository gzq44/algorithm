package bfs;

import java.util.*;

/**
 * bfs
 *
 * @author gzq44
 * @date 2023/08/04 18:30
 **/
public class BFS {

    public static void main(String[] args) {
//        new MaximumNumberofPointsFromGridQueries().maxPoints(new int[][]{{1, 2, 3}, {2, 5, 7}, {3, 5, 1}}, new int[]{5, 6, 2, 5, 6, 2});
        new DivideNodesIntotheMaximumNumberofGroups().magnificentSets(2, new int[][]{{1,2}});
    }
}

class MaximumNumberofPointsFromGridQueries {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int n = grid.length;
        int m = grid[0].length;
        // ArrayDeque<int[]> d = new ArrayDeque();
        //int[] -> r c v
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> {
            return o1[2] - o2[2];
        });
        pq.add(new int[]{0, 0, grid[0][0]});
        int[][] clone = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            clone[i] = new int[]{queries[i], i};
        }
        Arrays.sort(clone, ((o1, o2) -> {
            return o1[0] - o2[0];
        }));
        HashSet<Integer> set = new HashSet();
        set.add(0);
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int[] res = new int[queries.length];
        int cnt = 0;
        for (int i = 0; i < queries.length; i++) {

            int q = clone[i][0];
            while (!pq.isEmpty() && pq.peek()[2] < q) {
                int[] poll = pq.poll();
                cnt++;
                for (int[] d : dirs) {
                    int nx = poll[0] + d[0];
                    int ny = poll[1] + d[1];
                    int k = nx * 10000 + ny;
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && !set.contains(k)) {
                        set.add(k);
                        pq.add(new int[]{nx, ny, grid[nx][ny]});
                    }
                }
            }
            res[i] = cnt;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[clone[i][1]] = res[i];
        }
        return ans;
    }
}

class DivideNodesIntotheMaximumNumberofGroups {
    public int magnificentSets(int n, int[][] edges) {
        int m = edges.length;
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        //判断点是否存在奇环
        //计算bfs深度
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            // -i 0 i
            ArrayDeque<Integer> d = new ArrayDeque();
            d.offer(i);
            res[i] = res[i] == 0 ? i : res[i];
            int step = 0;
            boolean[] vis = new boolean[n + 1];
            vis[i] = true;
            while (!d.isEmpty()) {
                step++;
                int size = d.size();
                for (int l = 0; l < size; l++) {
                    int poll = d.poll();
                    for (int j = 0; j < g[poll].size(); j++) {
                        int idx = g[poll].get(j);
                        if (res[poll] == res[idx]) {
                            return -1;
                        } else if (!vis[idx]) {
                            vis[idx] = true;
                            res[idx] = -res[poll];
                            d.offerLast(idx);
                        }
                    }
                }
            }
            int v = map.getOrDefault(Math.abs(res[i]), 0);
            if (v < step) ans += step - v;
            map.put(Math.abs(res[i]), Math.max(v, step));
        }
        return ans;
    }
}

