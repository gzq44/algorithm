import java.io.*;

import java.util.StringTokenizer;

public class Main {

    public Scanner in = new Scanner();
    public PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        Scanner sc = new Scanner();
        int t = sc.nextInt();
        while(t-- > 0){
            int xor = sc.nextInt();
            int ans = xor >> 1;
            if((xor & 1) > 0 || (xor & ans) > 0 ){
                System.out.println(-1);
            }else{
                System.out.println((xor | ans) + " "+ ans);
            }
        }
    }

    public void solve() {
        int n = in.nextInt();
        out.flush();
    }

    void f(int[] arr) {
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