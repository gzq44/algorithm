package combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * 1916
 *
 * 组合 + 树形dp
 * 附组合模板
 * @author gzq44
 * @date 2023/12/23 22:25
 **/
class Problem1916 {
    //template
    static final int M = 1000000007;
    static final long[] FAC = new long[100000];// FAC[i] 表示 i!
    static final long[] INV = new long[100000];// INV[i] 表示 i! 的乘法逆元
    static {
        FAC[0] = INV[0] = 1;
        for (int i = 1; i < 100000; ++i) {
            FAC[i] = FAC[i - 1] * i % M;
            INV[i] = fastPower(FAC[i], M - 2);
        }
    }
    static long fastPower(long a, int b) {
        if (b == 0) return 1;
        if ((b & 1) == 1) return a * fastPower(a, b ^ 1) % M;
        long p = fastPower(a, b >> 1);
        return p * p % M;
    }

    //树形dp
    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        List<List<Integer>> tree = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) tree.add(new ArrayList<>());
        for (int i = 1; i < n; ++i) tree.get(prevRoom[i]).add(i);
        return (int) postorder(tree, new int[n], 0);
    }

    long postorder(List<List<Integer>> tree, int[] size, int u) {
        long dpu = 1;
        size[u] = 1;
        for (int v : tree.get(u)) {
            dpu *= postorder(tree, size, v);// dp[u] *= dp[v]
            dpu %= M;
            dpu *= INV[size[v]];// dp[u] *= 乘法逆元(size[v]!)
            dpu %= M;
            size[u] += size[v];
        }
        dpu *= FAC[size[u] - 1];// dp[u] *= (size[u] - 1)!
        dpu %= M;
        return dpu;
    }
}
