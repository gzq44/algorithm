import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 489B
 *
 * @author gzq44
 * @date 2024/01/29 23:27
 **/
public class Problem489B {
    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new Problem489B().solve();
    }

    public void solve() {
        int n = in.nextInt();
        int[] arr1 = in.readArray(n);
        int m = in.nextInt();
        int[] arr2 = in.readArray(m);
        ArrayList<int[]> list = new ArrayList<>();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int ans = 0;
        for (int i1 = 0, i2 = 0; i1 < n && i2 < m; ) {
            if (Math.abs(arr1[i1] - arr2[i2]) <= 1) {
                i1++;
                i2++;
                ans++;
            } else if (arr1[i1] < arr2[i2]) {
                i1++;
            } else if (arr1[i1] > arr2[i2]) {
                i2++;
            }
        }
        out.println(ans);
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
