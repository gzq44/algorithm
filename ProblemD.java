import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class ProblemD {

    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new ProblemD().solve();
    }

    public void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            int[] arr1 = in.readArray(n1);
            int[] arr2 = in.readArray(n2);
            long ans = f(arr1, arr2);
            out.println(ans);
        }
        out.flush();
    }
    long f(int[] a1, int[] a2) {
        int n1 = a1.length;
        int n2 = a2.length;
        Arrays.sort(a1);
        Arrays.sort(a2);
        int l = 0;
        int r = n2 - 1;
        long res = 0;
        for (int i = 0; i < (n1 + 1) / 2; i++) {
            int ll = Math.abs(a1[i] - a2[r]);
            int rr = Math.abs(a1[n1 - 1 - i] - a2[l]);
            if (n1 % 2 == 1 && i == n1 / 2) {
                res += Math.max(ll, rr);
            } else {
                res += ll + rr;
                l++;
                r--;
            }
        }
        return res;
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
