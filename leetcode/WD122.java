import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 双周122
 *
 * @author gzq44
 * @date 2024/01/20 22:34
 **/
public class WD122 {


    public static void main(String[] args) {
//        new WD122().minimumCost(new int[]{1,1,3,10});
        new WD122().canSortArray(new int[]{8,4,2,30,15});
    }
    public int minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.min(ans, nums[i] + nums[j] + nums[0]);
            }
        }
        return ans;
    }

    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new int[]{i, nums[i]});
        }
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt1 = 0;
            int p1 = nums[i];
            while(p1 !=0) {
                p1 = p1 & (p1 -1);
                cnt1++;
            }
            cnt[i] = cnt1;
        }
        int[] diff = solve(n, cnt);
        res.sort(((o1, o2) -> o1[1] - o2[1]));
        for (int i = 0; i < n; i++) {
            if (!f(nums[i], res.get(i)[1]) || diff[res.get(i)[0]] != diff[i]) return false;
        }
        return true;
    }

    int[] solve(int n, int[] arr) {
        int[] diff = new int[n]; // 差分数组
        diff[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) diff[i] = diff[i - 1];
            else diff[i] = diff[i - 1] + 1;
        }
        return diff;
    }
    boolean f(int p1, int p2) {
        int cnt1 = 0;
        while(p1 !=0) {
            p1 = p1 & (p1 -1);
            cnt1++;
        }
        int cnt2 = 0;
        while(p2 !=0) {
            p2 = p2 & (p2 -1);
            cnt2++;
        }
        return cnt1 == cnt2;
    }

    public int minimumArrayLength(int[] nums) {
        int n = nums.length;
        int f1 = 0;
        int f2 = n - 1;
        int ans = 0;
        while (f1 < f2) {
            if (nums[f1] % nums[f2] == 0) {
                ans++;
                f1++;
            }
            f2--;
        }
        return ans == 0 ?  1 : ans + (f1 == f2 ? 1 : 0);
    }
}
