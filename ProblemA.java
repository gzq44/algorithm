import java.io.*;
import java.util.StringTokenizer;

public class ProblemA {

    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new ProblemA().solve();
    }

    public void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int[] a1 = in.readArray(2);
            int[] a2 = in.readArray(2);
            int[] a3 = in.readArray(2);
            int[] a4 = in.readArray(2);
            if (a1[0] == a2[0]) out.println((int) Math.pow(a1[1] - a2[1], 2));
            else out.println((int) Math.pow( a1[0] - a2[0], 2));
        }
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