import java.io.*;
import java.util.StringTokenizer;
public class Problem1748B {
    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        new Problem1748B().solve();
    }

    //计算有多少个多样的子字串-重复次数不超过不同字母的次数
    public void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            String s = in.next();
            long ans = f(s);
            out.println(ans);
        }

        out.flush();
    }
    long f(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        long ans = 0;
        //有多少个不同数字-最多十个=窗口大小不大于100
        for (int i = 1; i <= Math.min(100, n); i++) {
            //记录数字出现的次数
            int[] cnt = new int[10];
            //记录不同的数字
            int diff = 0;
            for (int j = 0; j < n; j++) {
                int num = arr[j] - '0';
                if (cnt[num]++ == 0) diff++;
                if (j < i - 1) continue;
                if (check(cnt, diff)) ans++;
                if (--cnt[arr[j - i + 1] - '0' ] == 0) diff--;
            }
        }
        return ans;
    }

    boolean check(int[] cnt, int k) {
        for (int num : cnt) {
            if (num > k) return false;
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
