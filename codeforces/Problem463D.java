import java.io.*;
import java.util.StringTokenizer;

/**
 * 463D
 *
 * @author gzq44
 * @date 2024/01/05 21:58
 **/
public class Problem463D {
    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new Problem463D().solve();
    }

    public void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[m][n];
        int[][] pos = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = in.nextInt();
                arr[i][j] = v;
                pos[i][v] = j;
            }
        }
        int[] a = arr[0];
        int[] dp = new int[n];
        int mx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (f(pos, a[i], a[j])) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            mx = Math.max(dp[i], mx);
        }
        out.println(mx + 1);
        out.flush();
    }
    //检查其他序列的a[i] 是否在a[j]右边，最多遍历五次
    boolean f(int[][] pos, int ai, int aj) {
        int m = pos.length;
        int n = pos[0].length;
        for (int i = 0; i < m; i++) {
            if (pos[i][ai] < pos[i][aj]) return false;
        }
        return true;
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
