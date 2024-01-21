import java.io.*;
import java.util.StringTokenizer;

public class ProblemB {

    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new ProblemB().solve();
    }

    public void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            String s1 = in.next();
            String s2 = in.next();
            int ans = f(s1, s2);
            out.println(ans);
        }
        out.flush();
    }
    int f(String s1, String s2) {
        int zeroDiff = 0;
        int oneDiff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == '0') zeroDiff++;
                else oneDiff++;
            }
        }
        return Math.max(zeroDiff, oneDiff);
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