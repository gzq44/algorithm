package template;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 乘法
 *
 * @author gzq44
 * @date 2024/01/08 22:49
 **/
public class SegmentTreeMulipleAndPlus {
    static long[] tree = new long[100000 << 2], mark1 = new long[100000 << 2], mark2 = new long[100000 << 2];//mark懒人标记
    static int[] A;//存储数据输入
    static int MOD;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        PrintWriter pr = new PrintWriter(System.out);
        st.nextToken();
        int n = (int) st.nval;
        st.nextToken();
        int m = (int) st.nval;
        st.nextToken();
        MOD = (int) st.nval;
        A = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st.nextToken();
            A[i] = (int) st.nval;
        }
        build(1, n, 1);//建树
        Arrays.fill(mark1, 1L);
        for (int i = 1; i <= m; i++) {
            st.nextToken();
            int t = (int) st.nval;
            st.nextToken();
            int x = (int) st.nval;
            st.nextToken();
            int y = (int) st.nval;
            if (t == 1) {
                st.nextToken();
                int k = (int) st.nval;
                update1(1, k, 1, n, x, y);
            } else if (t == 2) {
                st.nextToken();
                int k = (int) st.nval;
                update2(1, k, 1, n, x, y);
            } else {
                pr.println(query(1, 1, n, x, y));
            }
        }
        pr.flush();
    }

    private static void pushDown(int p, int l, int r) {
        if (l == r) return;
        int m = l + ((r - l) >> 1);
        tree[p << 1] = (tree[p << 1] * mark1[p] + mark2[p] * (m - l + 1)) % MOD;
        tree[p << 1 | 1] = (tree[p << 1 | 1] * mark1[p] + mark2[p] * (r - m)) % MOD;

        mark1[p << 1] = (mark1[p << 1] * mark1[p]) % MOD;
        mark1[p << 1 | 1] = (mark1[p << 1 | 1] * mark1[p]) % MOD;
        mark2[p << 1] = (mark2[p << 1] * mark1[p] + mark2[p]) % MOD;
        mark2[p << 1 | 1] = (mark2[p << 1 | 1] * mark1[p] + mark2[p]) % MOD;


        mark1[p] = 1;mark2[p] = 0;
    }

    private static void update1(int p, int d, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            tree[p] = tree[p] * d % MOD;
            mark2[p] = mark2[p] * d % MOD;
            mark1[p] = mark1[p] * d % MOD;
            return;
        }
        pushDown(p, l, r);
        int m = l + ((r - l) >> 1);
        if (L <= m) update1(p << 1, d, l, m, L, R);
        if (R > m) update1(p << 1 | 1, d, m + 1, r, L, R);
        tree[p] = (tree[p << 1] + tree[p << 1 | 1]) % MOD;
    }

    private static void update2(int p, int d, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            tree[p] = (tree[p] + d * (r - l + 1)) % MOD;
            mark2[p] = (mark2[p] + d) % MOD;
            return;
        }
        pushDown(p, l, r);
        int m = l + ((r - l) >> 1);
        if (L <= m) update2(p << 1, d, l, m, L, R);
        if (R > m) update2(p << 1 | 1, d, m + 1, r, L, R);
        tree[p] = (tree[p << 1] + tree[p << 1 | 1]) % MOD;
    }

    private static void build(int l, int r, int p) {
        if (l == r) {
            tree[p] = A[l];
            return;
        }
        int m = l + ((r - l) >> 1);
        build(l, m, p << 1);
        build(m + 1, r, p << 1 | 1);
        tree[p] = (tree[p << 1] + tree[p << 1 | 1]) % MOD;
    }


    private static long query(int p, int l, int r, int L, int R) {
        if (L <= l && r <= R) return tree[p];
        pushDown(p, l, r);
        int m = l + ((r - l) >> 1);
        long ans = 0;
        if (L <= m) ans = (ans + query(p << 1, l, m, L, R)) % MOD;
        if (R > m) ans = (ans + query(p << 1 | 1, m + 1, r, L, R)) % MOD;
        return ans % MOD;
    }
}
