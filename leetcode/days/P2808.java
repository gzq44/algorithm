package days;

import java.util.*;

/**
 * 2808
 *
 * @author gzq44
 * @date 2024/01/30 23:01
 **/
public class P2808 {

    public static void main(String[] args) {
        new P2808().minimumSeconds(Arrays.asList(new Integer[]{1,1,1,1}));
    }

    public int minimumSeconds(List<Integer> nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            List<Integer> orDefault = map.getOrDefault(nums.get(i), new ArrayList<>());
            orDefault.add(i);
            map.put(nums.get(i), orDefault);
        }
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
            List<Integer> res = e.getValue();
            if (res.size() == 1) ans = Math.min(ans, n / 2);
            else {
                int cnt = (res.get(0) + n - res.get(res.size() - 1)) / 2;
                for (int i = 0; i < res.size() - 1; i++) {
                    cnt = Math.max(cnt, (res.get(i + 1) - res.get(i)) / 2);
                }
                ans = Math.min(ans, cnt);
            }
        }
        return ans;
    }
}
