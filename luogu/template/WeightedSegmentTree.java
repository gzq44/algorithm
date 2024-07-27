package template;

import java.util.Arrays;

/**
 * 权值线段树
 *
 * @author gzq44
 * @date 2024/03/03 10:48
 **/
class SegmentTreeWeight {
    long[] seg;

    public SegmentTreeWeight(int[] a, int n) {
        seg = new long[4 * n];
        build(a, 0, n - 1, 0);
    }

    void build(int[] a, int begin, int end, int si) {
        if (begin == end) {
            seg[si] = 0;
            return;
        }
        int mid = (begin + end) >> 1;
        build(a, begin, mid, si * 2 + 1);
        build(a, mid + 1, end, si * 2 + 2);
        seg[si] = seg[si * 2 + 1] + seg[si * 2 + 2];
    }

    void update(int begin, int end, int si, int uidx, int diff) {
        if (begin == end) {
            if (begin != uidx) throw new RuntimeException("update: begin != uidx");
            seg[si] += diff;
            return;
        }
        int mid = (begin + end) >> 1;
        if (uidx <= mid) {
            update(begin, mid, si * 2 + 1, uidx, diff);
        } else {
            update(mid + 1, end, si * 2 + 2, uidx, diff);
        }
        seg[si] = seg[si * 2 + 1] + seg[si * 2 + 2];
    }

    long queryRange(int begin, int end, int si, int l, int r) {
        if (l <= begin && end <= r) {
            return seg[si];
        }
        int mid = (begin + end) >> 1;
        int result = 0;
        if (l <= mid) result += queryRange(begin, mid, si * 2 + 1, l, r);
        if (r > mid) result += queryRange(mid + 1, end, si * 2 + 2, l, r);
        return result;
    }

    // sum[r] - sum[l - 1] < t
    // sum[l - 1] > sum[r] - t
    // sum[0] sum[1] ... sum[r]
    long sol(long t, long[] a) {
        long[] sum = new long[a.length];
        long[] tmp = new long[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            sum[i] = (i > 0 ? sum[i - 1] : 0) + a[i];
        }
//            Utils.printArray(sum);
        System.arraycopy(sum, 0, tmp, 0, a.length);
        Arrays.sort(tmp);
//            Utils.printArray(tmp);
        int segsize = a.length + 1;
        SegmentTreeWeight segtree = new SegmentTreeWeight(null, segsize);
        segtree.update(0, segsize - 1, 0, lower_bound(tmp, 0), 1);
        long result = 0;
        for (long n : sum) {
//                Utils.printf("n-t=%d lb(n-t)=%d lb(n)=%d\n", n - t, lower_bound(tmp, n - t), lower_bound(tmp, n));
            long cnt = segtree.queryRange(0, segsize - 1, 0, lower_bound(tmp, n - t + 1), segsize - 1);
//                System.out.printf("n=%d cnt=%d\n", n, cnt);
            result += cnt;
            segtree.update(0, segsize - 1, 0, lower_bound(tmp, n), 1);
        }
        return result;
    }

    int lower_bound(long[] a, long v) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (a[m] < v) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r + 1;
    }
    public static void main(String[] args) {
        SegmentTreeWeight segmentTreeWeight = new SegmentTreeWeight(null, 100000 << 2);
    }

}
