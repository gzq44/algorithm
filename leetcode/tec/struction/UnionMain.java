package tec.struction;


/**
 * 并查集
 *
 * @author gzq44
 * @date 2024/08/04 17:29
 **/
public class UnionMain {


    public static void main(String[] args) {
        new UnionMain().shortestDistanceAfterQueries(5, new int[][]{{2, 4}, {0, 2}, {0, 4}});
    }

    /**
     *
     *      并查集查连通块（union过程）
     *      https://leetcode.cn/problems/shortest-distance-after-road-addition-queries-ii/description/
     *
     * @param n
     * @param edges
     * @return
     */
    public int[] shortestDistanceAfterQueries(int n, int[][] edges) {
        Union1 union = new Union1(n);
        int m = edges.length;
        int[] ans = new int[m];
        int cnt = n - 1;
        for (int i = 0; i < m; i++) {
            int[] e = edges[i];
            int l = e[0];
            int r = e[1] - 1;
            int p = union.find(r);
            for (int j = union.find(l); j < r; j = union.find(j + 1)) {
                union.parent[j] = p;
                cnt--;
            }
            ans[i] = cnt;
        }
        return ans;
    }

    class Union1 {
        int[] parent;
        public Union1(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] == x) return x;
            else return parent[x] = find(parent[x]);
        }
    }
}
