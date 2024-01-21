import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 1915E
 *
 * @author gzq44
 * @date 2024/01/08 22:20
 **/
public class Problem1915E {

    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new Problem1915E().solve();
    }

    public void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            int[] arr = in.readArray(m);
            boolean flag = f(arr);
            out.println(flag ? "YES" : "NO");
        }
        out.flush();
    }
    boolean f(int[] arr) {
        long sum = 0;
        HashSet<Long> set = new HashSet<>();
        set.add(0L);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) sum += arr[i];
            else sum -= arr[i];
            if (set.contains(sum)) return true;
            set.add(sum);
        }
        return false;
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
