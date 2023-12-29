package apriceshittest;


import org.omg.PortableInterceptor.INACTIVE;
import utils.TreeNode;

import java.util.*;

/**
 * shit
 *
 * @author gzq44
 * @date 2023/08/08 21:04
 **/
public class Main {

    public static void main(String[] args) {
        new Main().minCost(new int[]{20,1,15}, 5);
    }

    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long tmp = 0;
            for (int j = 0; j < n; j++) {
                res[j] = Math.min(res[j], nums[(j + i) % n]);
                tmp += res[j];
            }
            ans = Math.min(ans, tmp + (long)i * x);
        }
        return ans;
    }
}

