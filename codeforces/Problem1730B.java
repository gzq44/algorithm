import java.util.*;
import java.io.*;
public class Problem1730B {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static double eps=0.001;

    public static void main(String[] args) throws Exception {
        int caseNum = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= caseNum; testCase++) {
            int n = Integer.parseInt(br.readLine());
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = readInt();
            }
            br.readLine();
            int[] t = new int[n];
            for (int i = 0; i < n; i++) {
                t[i] = readInt();
            }
            br.readLine();
            double x0=x[0];
            double t0=t[0];
            for(int i=1;i<n;i++) {
                double x1=x[i];
                double t1=t[i];
                if(t1-t0>Math.abs(x1-x0)) {
                    x0=x1;
                    t0=t1;
                }else if(x1>x0 && x1-x0>Math.abs(t1-t0)-eps) {
                    double xNew=(x1+x0+t1-t0)/2;
                    double tNew=(x1-x0+t1+t0)/2;
                    x0=xNew;
                    t0=tNew;
                }else if(x1<x0 && x0-x1>Math.abs(t1-t0)-eps) {
                    double xNew=(x1+x0+t0-t1)/2;
                    double tNew=(x0-x1+t1+t0)/2;
                    x0=xNew;
                    t0=tNew;
                }
            }
            System.out.printf("%.1f\n",x0);
        }
    }

    public static int readInt() {
        int input = 0;
        int c;
        int sign = 1;
        try {
            while ((c = br.read()) == '-') {
                sign = -sign;
            }
            input *= 10;
            input += (c - 48);
            while ((c = br.read()) > 32) {
                input *= 10;
                input += (c - 48);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (sign == 1) ? input : -input;
    }

    public static long readLong() {
        long input = 0;
        int c;
        int sign = 1;
        try {
            while ((c = br.read()) == '-') {
                sign = -sign;
            }
            input *= 10;
            input += (c - 48);
            while ((c = br.read()) > 32) {
                input *= 10;
                input += (c - 48);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (sign == 1) ? input : -input;
    }
}
