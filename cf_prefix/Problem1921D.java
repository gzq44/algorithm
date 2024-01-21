import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem1921D {

    public static Scanner in = new Scanner();
    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    static void sort(int[] a,int low,int high){
        if(low<high){
            int mid=low+(high-low)/2;
            sort(a,low,mid);
            sort(a,mid+1,high);
            merge(a,low,mid,mid+1,high);
        }
    }
    static void merge(int[] a,int l1,int h1,int l2,int h2){
        int[] a1=new int[h1-l1+1];
        int[] b1=new int[h2-l2+1];
        for(int j=0;j<a1.length;j++){
            a1[j]=a[j+l1];
        }
        for(int j=0;j<b1.length;j++){
            b1[j]=a[j+l2];
        }
        int j=0;
        int i=0;
        while(j<a1.length && i<b1.length){
            if(a1[j]>b1[i]){
                a[l1]=b1[i];
                i++;
            }else{
                a[l1]=a1[j];
                j++;
            }
            l1++;
        }
        while(j<a1.length){
            a[l1]=a1[j];
            j++;
            l1++;
        }
        while(i<b1.length){
            a[l1]=b1[i];
            i++;
            l1++;
        }
    }
    public static void main(String[] args) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int n1 = in.nextInt();
            int n2 = in.nextInt();
            int[] arr1 = new int[n1];
            for (int i1 = 0; i1 < n1; i1++) {
                arr1[i1] = in.nextInt();
            }
            int[] arr2 = new int[n2];
            for (int i1 = 0; i1 < n2; i1++) {
                arr2[i1] = in.nextInt();
            }
            sort(arr1, 0, n1 - 1);
            sort(arr2, 0, n2 - 1);
            long ans = f(arr1, arr2);
            out.println(ans);
        }
        out.flush();
    }

    public void solve() {

    }
    static long f(int[] a1, int[] a2) {
        int n1 = a1.length;
        int n2 = a2.length;
        long[] p1 = new long[n1 + 1];
        long[] p2 = new long[n2 + 1];
        for (int i = 0; i < n1; i++) {
            p1[i + 1] = p1[i] + a1[i];
        }
        for (int i = 0; i < n2; i++) {
            p2[i + 1] = p2[i] + a2[i];
        }
        long ans = 0;
        for (int k = 0; k <= n1; k++) {
            long l = p2[n2] - p2[n2 - (n1 - k)] - (p1[n1 - k] - p1[0]);
            long r = p1[n1] - p1[n1 - k] - (p2[k] - p2[0]);
            ans = Math.max(ans, l + r);
        }
        return ans;
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
