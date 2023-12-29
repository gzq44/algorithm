package dp.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 100141
 * https://leetcode.cn/problems/find-number-of-coins-to-place-in-tree-nodes/
 *
 * @author gzq44
 * @date 2023/12/24 12:05
 **/
public class P2973 {

    public static void main(String[] args) {
        new P2973().placedCoins(new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9}, {0, 10}, {0, 11}, {0, 12}, {0, 13}, {0, 14}, {0, 15}, {0, 16}, {0, 17}, {0, 18}, {0, 19}, {0, 20}, {0, 21}, {0, 22}, {0, 23}, {0, 24}, {0, 25}, {0, 26}, {0, 27}, {0, 28}, {0, 29}, {0, 30}, {0, 31}, {0, 32}, {0, 33}, {0, 34}, {0, 35}, {0, 36}, {0, 37}, {0, 38}, {0, 39}, {0, 40}, {0, 41}, {0, 42}, {0, 43}, {0, 44}, {0, 45}, {0, 46}, {0, 47}, {0, 48}, {0, 49}, {0, 50}, {0, 51}, {0, 52}, {0, 53}, {0, 54}, {0, 55}, {0, 56}, {0, 57}, {0, 58}, {0, 59}, {0, 60}, {0, 61}, {0, 62}, {0, 63}, {0, 64}, {0, 65}, {0, 66}, {0, 67}, {0, 68}, {0, 69}, {0, 70}, {0, 71}, {0, 72}, {0, 73}, {0, 74}, {0, 75}, {0, 76}, {0, 77}, {0, 78}, {0, 79}, {0, 80}, {0, 81}, {0, 82}, {0, 83}, {0, 84}, {0, 85}, {0, 86}, {0, 87}, {0, 88}, {0, 89}, {0, 90}, {0, 91}, {0, 92}, {0, 93}, {0, 94}, {0, 95}, {0, 96}, {0, 97}, {0, 98}, {0, 99}}, new int[]{-5959, 602, -6457, 7055, -1462, 6347, 7226, -8422, -6088, 2997, -7909, 6433, 5217, 3294, -3792, 7463, 8538, -3811, 5009, 151, 5659, 4458, -1702, -1877, 2799, 9861, -9668, -1765, 2181, -8128, 7046, 9529, 6202, -8026, 6464, 1345, 121, 1922, 7274, -1227, -9914, 3025, 1046, -9368, -7368, 6205, -6342, 8091, -6732, -7620, 3276, 5136, 6871, 4823, -1885, -4005, -3974, -2725, -3845, -8508, 7201, -9566, -7236, -3386, 4021, 6793, -8759, 5066, 5879, -5171, 1011, 1242, 8536, -8405, -9646, -214, 2251, -9934, -8820, 6206, 1006, 1318, -9712, 7230, 5608, -4601, 9185, 346, 3056, 8913, -2454, -3445, -4295, 4802, -8852, -6121, -4538, -5580, -9246, -6462});
    }

    static long[] ans;
    static List<Integer>[] e;
    static int[] cost;
    public long[] placedCoins(int[][] edges, int[] _cost) {
        cost = _cost;
        int n = cost.length;
        e = new List[n];
        ans = new long[n];
        Arrays.setAll(e, ArrayList::new);
        Arrays.setAll(e, e -> new ArrayList<>());
        for (int[] edge : edges) {
            e[edge[0]].add(edge[1]);
            e[edge[1]].add(edge[0]);
        }
        dfs(0, -1);
        return ans;
    }

    private List<Integer> dfs(int i, int fa) {
        List<Integer> a = new ArrayList<>();
        for (Integer next : e[i]) {
            if (next != fa) {
                List<Integer> tmp = dfs(next, i);
                a.addAll(tmp);
            }
        }
        a.add(cost[i]);
        a.sort(((o1, o2) -> o1 - o2));
        long sum = 1;
        if (a.size() < 3) {
            ans[i] = sum;
        } else {
            int m = a.size();
            ans[i] = Math.max((long) a.get(m - 3) * a.get(m - 2) * a.get(m - 1), Math.max((long) a.get(0) * a.get(1) * a.get(m - 1), 0));
        }
        if (a.size() > 5) {
            a = Arrays.asList(new Integer[]{a.get(0), a.get(1), a.get(a.size() - 3), a.get(a.size() - 1), a.get(a.size() - 2)});
        }
        return a;
    }


}
