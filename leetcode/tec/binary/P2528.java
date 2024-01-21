package tec.binary;

/**
 * 2528
 *
 * @author gzq44
 * @date 2024/01/04 21:35
 **/
public class P2528 {

    public static void main(String[] args) {
        new P2528().maxPower(new int[]{1,2,4,5,0}, 1, 2);
    }
    //前缀 + 差分 + 二分
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stations[i];
        }
        long mn = Long.MAX_VALUE;
        long[] ss = new long[n];
        for (int i = 0; i < n; i++) {
            int left = i - r < 0 ? 0 : i - r;
            int right = i + r  + 1 < n ? i + r + 1 : n;
            ss[i] = prefix[right] - prefix[left];
            mn = Math.min(mn, ss[i]);
        }
        long left = mn;
        long right = mn + k;
        while (left < right) {
            long mid = left + (right - left + 1) / 2;
            if (f(ss, r, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    boolean f(long[] ss, int r, int k, long t) {
        int cnt = 0;
        int n = ss.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            cnt += diff[i];
            long x = t - ss[i] - cnt;
            if (x > 0) {
                if (k < x) return false;
                cnt += x;
                k -= x;
                if (i + r * 2 + 1 < n) diff[i + r * 2 + 1] -= x;
            }
        }
        return true;
    }
}
