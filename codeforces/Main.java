
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static InputReader in = new InputReader(System.in);
    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) {
        new Main().solve();
        out.flush();
    }

    public void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            int[] arr = new int[m];
            for (int j = 0; j < m; j++) {
                arr[j] = in.nextInt();
            }
            f(arr);
        }

    }

    void f(int[] arr) {
        int n = arr.length;
        int[] suf = new int[n];
        int cnt = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= arr[i + 1]) cnt++;
            suf[i] = cnt;
        }
        int pre = 1;
        int ans = cnt;
        for (int i = 1; i < n - 1; i++) {
            if (arr[i - 1] <= arr[i]) pre++;
            ans = Math.min(ans, pre + suf[i + 1]);
        }
        ans = Math.min(ans, pre);
        out.println(ans);
    }



    static class InputReader {
        private final static int BUF_SZ = 65536;
        BufferedReader in;
        StringTokenizer tokenizer;

        public InputReader(InputStream in) {
            super();
            this.in = new BufferedReader(new InputStreamReader(in), BUF_SZ);
            tokenizer = new StringTokenizer("");
        }

        private String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

    }
}