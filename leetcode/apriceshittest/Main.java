package apriceshittest;


import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * shit
 *
 * @author gzq44
 * @date 2023/08/08 21:04
 **/
public class Main {
    public static void main(String[] args) {
        new Main().minimumFuelCost(new int[][]{{3,1},{3,2},{1,0},{0,4},{0,5},{4,6}}, 2);
    }
    int n;
    ArrayList<List<Integer>> edges;
    int ans = 0;
    int s;
    public long minimumFuelCost(int[][] roads, int seats) {
        n = roads.length;
        edges = new ArrayList<>();
        s = seats;
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < roads.length; i++) {
            int[] road = roads[i];
            edges.get(road[0]).add(road[1]);
            edges.get(road[1]).add(road[0]);
        }
        dfs(0, 0, s);
        return ans;
    }

    //i 当前坐标，ss 剩余座位
    void dfs (int i, int depth,int ss) {
        if (edges.get(i).size() == 0) {
            return;
        }
        for (Integer e : edges.get(i)) {
            if (ss == 0) {
                ans += depth;
                dfs(e, depth + 1, s - 1);
            }
            dfs(e, depth + 1, ss - 1);
        }
    }
}
