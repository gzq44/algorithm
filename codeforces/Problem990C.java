import java.io.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author gzq
 * @date 2024/01/10  16:56
 */
public class Problem990C {
    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) {
        new Problem990C().solve();
    }

    public void solve() {
        int n = in.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = in.next();
        }
        long ans = f(strs);
        out.println(ans);
        out.flush();
    }


    long f(String[] strs) {
        HashMap<Integer, Long> left = new HashMap<>();
        HashMap<Integer, Long> right = new HashMap<>();
        long zero = 0;
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            int count = count(strs[i]);
            if (count == Integer.MAX_VALUE) continue;
            if (count == 0) {
                zero++;
            } else if (count > 0) {
                left.put(count, left.getOrDefault(count, 0L) + 1);
            } else {
                right.put(count, right.getOrDefault(count, 0L) + 1);
            }
        }
        long ans = 0;
        for (Map.Entry<Integer, Long> e : left.entrySet()) {
            Integer key = e.getKey();
            long v1 = e.getValue();
            if (right.containsKey(-key)) {
                long v2 = right.get(-key);
                ans += v1 * v2;
            }
        }
        return ans + zero * zero;
    }

    //stack
    int count(String s) {
        int n = s.length();
        int cnt = 0;
        ArrayDeque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i);
            if (!d.isEmpty() && d.peek() == '(' && c == ')') d.poll();
            else d.push(c);
        }
        int f1 = 0; int f2 = 0;
        while (!d.isEmpty()) {
            Integer poll = d.poll();
            if (poll == '(') f1++;
            if (poll == ')') f2--;
        }
        if (f1 > 0 && f2 < 0) return Integer.MAX_VALUE;
        return f1 == 0 ? f2 : f1;
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
