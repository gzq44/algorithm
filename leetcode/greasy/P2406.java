package greasy;

import tec.diff.P2772;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2406
 *
 * @author gzq44
 * @date 2024/01/01 17:31
 **/
public class P2406 {

    public static void main(String[] args) {
        new P2406().minGroups(new int[][]{{5,10},{6,8},{1,5},{2,3},{1,10}});
    }

    public int minGroups(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        Arrays.sort(intervals, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
        int ans = 0;
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int[] arr = intervals[i];
            if (!pq.isEmpty() && pq.peek() < arr[0]) {
                Integer poll = pq.poll();
            } else {
                ans++;
            }
            pq.add(arr[1]);
        }
        return ans;
    }
}
