package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 单调栈
 *
 * @author gzq44
 * @date 2023/08/18 10:26
 **/
public class PriorityStack {


}
/**
 * https://leetcode.cn/problems/apply-operations-to-maximize-score/description/
 *
 * @author gzq44
 * @date 2023/08/18 10:26
 **/
class ApplyOperationstoMaximizeScore {
    private static final long MOD = (long) 1e9 + 7;
    private static final int MX = (int) 1e5 + 1;
    private static final int[] omega = new int[MX];
    static {
        for (int i = 2; i < MX; i++) {
            if (omega[i] == 0) {
                if ((int) i * i < MX) {
                    for (int j = i * i; j < MX; j += i) {
                        omega[i]++;
                    }
                }
            }
        }
    }
    //预处理 分解质数
    //贡献 单调栈计算左右边界
    //k = 10^9 当贡献的子数组过多的时候快速幂
    public int maximumScore(List<Integer> nums, int k) {
        int[] a = nums.stream().mapToInt(i -> i).toArray();
        int n = a.length;
        int[] left = new int[n];
        int[] right = new int[n];
        ArrayDeque<Integer> d = new ArrayDeque<>();
        return 0;
    }
}