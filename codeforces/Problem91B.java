import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 91
 *
 * @author gzq44
 * @date 2024/01/27 18:31
 **/
public class Problem91B {
    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new Problem91B().solve();
    }

    public void solve() {
        int n = in.nextInt();
        int[] arr = in.readArray(n);
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{arr[i], i});
        }
        list.sort(((o1, o2) -> o2[0] == o1[0] ? o2[1] - o1[1] : o2[0] - o1[0]));
        int[] ans = new int[n];
        int mn = -1;
        for (int i = n - 1; i >= 0; i--) {
            int[] d = list.get(i);
            if (mn == -1) {
                ans[d[1]] = -1;
            } else if (mn <= d[1]) {
                ans[d[1]] = -1;
            } else {
                ans[d[1]] = mn - d[1] - 1;
            }
            mn = Math.max(mn, d[1]);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(ans[i]);
            if (i != n - 1) {
                sb.append(" ");
            }
        }
        out.println(sb.toString());
        out.flush();
    }

    void f() {
    }


    static class Scanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int[] readArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = nextInt();
            return arr;
        }

        long[] readLong(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++)
                arr[i] = nextLong();
            return arr;
        }
    }
}
