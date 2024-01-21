import java.io.*;
import java.util.StringTokenizer;

public class ProblemC {
    
    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) {
        new ProblemC().solve();
    }
    
    public void solve() {
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int n = in.nextInt();
            int f = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int[] arr = in.readArray(n);
            boolean flag = f(arr, f, a, b);
            if (flag) out.println("YES");
            else out.println("NO");
        }
        out.flush();
    }
    boolean f(int[] arr, int f, int a, int b) {
        int n = arr.length;
        int pos = 0;
        for (int i = 0; i < n && f > 0; i++) {
            int cur = arr[i];
            if ((long)(cur - pos) * a < b) {
                //开着
                f -= (cur - pos) * a;
            } else {
                //关了
                f -= b;
            }
            pos = cur;
        }
        return f > 0;
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
