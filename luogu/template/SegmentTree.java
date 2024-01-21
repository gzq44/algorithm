package template;

/**
 * 线段树
 *
 * @author gzq44
 * @date 2024/01/06 00:01
 **/

import java.io.*;
import java.util.*;

public class SegmentTree {
    static long[] tree = new long[100000 << 2], mark = new long[100000 << 2];//mark懒人标记
    static int[] A;//存储数据输入

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);//使用StreamTokenizer快速输入
        PrintWriter pr = new PrintWriter(System.out);
        st.nextToken();
        int n = (int) st.nval;
        st.nextToken();
        int m = (int) st.nval;
        A = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st.nextToken();
            A[i] = (int) st.nval;
        }
        build(1, n, 1);

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
                update(x, y, k, 1, 1, n);
            } else {
                pr.println(query(x, y, 1, 1, n));
            }
        }
        pr.flush();//使用PrintWriter不要忘记flush
    }



    private static void build(int l, int r, int p) {
        if (l == r) {
            tree[p] = A[l];
            return;
        }
        int m = l + ((r - l) >> 1);
        build(l, m, p << 1);
        build(m + 1, r, p << 1 | 1);
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }

    private static void pushDown(int p, int l, int r) {
        if (l == r) return;
        int m = l + ((r - l) >> 1);
        tree[p << 1] += mark[p] * (m - l + 1);
        mark[p << 1] += mark[p];
        tree[p << 1 | 1] += mark[p] * (r - m);
        mark[p << 1 | 1] += mark[p];
        mark[p] = 0;
    }
    private static void update(int l, int r, int d, int p, int ll, int rr) {
        if (l <= ll && rr <= r) {
            tree[p] += d * (rr - ll + 1);
            mark[p] += d;
            return;
        }
        pushDown(p, ll, rr);
        int m = ll + ((rr - ll) >> 1);
        if (l <= m) update(l, r, d, p << 1, ll, m);
        if (r > m) update(l, r, d, p << 1 | 1, m + 1, rr);
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }
    private static long query(int l, int r, int p, int ll, int rr) {
        if (l <= ll && rr <= r) return tree[p];
        pushDown(p, ll, rr);
        int m = ll + ((rr - ll) >> 1);
        long ans = 0;
        if (l <= m) ans += query(l, r, p << 1, ll, m);
        if (r > m) ans += query(l, r, p << 1 | 1, m + 1, rr);
        return ans;
    }
}
