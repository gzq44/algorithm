package tec.diff;

import apriceshittest.Main;

/**
 * 2772
 *
 * @author gzq44
 * @date 2023/12/31 10:27
 **/
public class P2772 {

    public static void main(String[] args) {
        new P2772().checkArray(new int[]{2,2,3,1,1,0}, 3);
    }

    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) return true;
        int[] diff = new int[n];
        diff[0] = nums[0];
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

        for (int i = 0; i + k <= n; i++) {
            if (diff[i] < 0) return false;
            else if (diff[i] > 0) {
                if (i + k < n) diff[i + k] += diff[i];
                diff[i] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (diff[i] != 0) return false;
        }
        return true;
    }
}
