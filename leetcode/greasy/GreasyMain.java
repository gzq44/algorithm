package greasy;

import java.util.Arrays;

/**
 * 贪心
 *
 * @author gzq44
 * @date 2024/08/11 19:54
 **/
public class GreasyMain {

    /**
     *
     * @param s
     * @return
     */
    public int minSwaps(String s) {
        int v0 = f(s, 0);
        int v1 = f(s, 1);
        if (v0 >= 0 && v1 >= 0) return Math.min(v0, v1);
        else if (v0 >= 0) return v0;
        else return v1;
    }

    public int f(String s, int pre) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int ans = 0;
        int cnt0 = 0;
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            int c = arr[i] - '0';
            if (pre == c) {
                if (c == 0) {
                    if (cnt1 > 0) {
                        cnt1--;
                    } else {
                        ans++;
                        cnt0++;
                    }
                } else {
                    if (cnt0 > 0) {
                        cnt0--;
                    } else {
                        ans++;
                        cnt1++;
                    }
                }
            }
            pre ^= 1;
        }
        return cnt0 + cnt1 > 0 ? -1 : ans;
    }


    public static void main(String[] args) {
        new GreasyMain().minimumEffort(new int[][]{{1,1},{1,4},{1,4}});
    }
    /**
     * greasy + binary search
     * @param tasks
     * @return
     */
    public int minimumEffort(int[][] tasks) {
        int n = tasks.length;
        Arrays.sort(tasks, ((o1, o2) -> (o2[1] - o2[0] - o1[1] + o1[0]) == 0 ? o2[1] - o1[1] : o2[1] - o2[0] - o1[1] + o1[0]));
        int left = 0;
        int right = 0;
        for (int[] task : tasks) {
            left = Math.max(left, task[1]);
            right += task[0];
        }
        right += left;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(tasks, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean f(int[][] tasks, int cnt) {
        for (int[] task : tasks) {
            if (cnt < task[1]) {
                return false;
            }
            cnt -= task[0];
        }
        return true;
    }
}
