package stack;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 739
 *
 * @author gzq44
 * @date 2023/12/27 22:15
 **/
public class P739 {

    public static void main(String[] args) {
        new P739().dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        ArrayDeque<Integer> d = new ArrayDeque<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            while (!d.isEmpty() && temperatures[d.peek()] < temperatures[i]) {
                Integer poll = d.poll();
                ans[poll] = i - poll;
            }
            d.push(i);
        }
        return ans;
    }
}
