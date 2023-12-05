
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    public static InputReader in = new InputReader(System.in);
    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) {
//        int re = 1;
        int cnt = in.nextInt();

        Main main = new Main();

        for (int ri = 1; ri <= cnt; ri++) {
            main.solve();
        }

        out.flush();
    }

    public void solve() {
        int n = in.nextInt();
        long k = in.nextLong();
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            int second = n;
            int first = i;
            boolean f = true;
            for (int j = 0; j < k - 2; j++) {
                int tmp = first;
                first = second - first;
                second = tmp;
                f &= (first <= second);
                f &= (Math.min(first, second) >= 0);
                if (!f) break;
            }
            if (f) ans++;
        }
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