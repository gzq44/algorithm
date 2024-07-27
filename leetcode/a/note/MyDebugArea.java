package a.note;

import java.util.Arrays;
import java.util.List;

/**
 * debug
 *
 * @author gzq44
 * @date 2024/01/23 22:12
 **/
public class MyDebugArea {

    public static void main(String[] args) {
        new MyDebugArea().sumIndicesWithKSetBits(Arrays.asList(new Integer[]{5, 10, 1, 5, 2}), 1);
    }


    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Integer num = nums.get(i);
            if (f(i) == k) ans += num;
        }
        return ans;
    }

    int f(int num) {
        int res = 0;
        while (num > 0) {
            if ((num & 1) > 0) res++;
            num >>= 1;
        }
        return res;
    }

}
