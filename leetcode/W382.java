import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 382
 *
 * @author gzq44
 * @date 2024/01/28 10:34
 **/
public class W382 {
    public int countKeyChanges(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1] || arr[i] != (arr[i - 1] ^ 32)) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        new W382().maximumLength(new int[]{1,1,1,1,1,2,2,4,4,16,16,32});
    }
//    public int maximumLength(int[] nums) {
//        int n = nums.length;
//        HashMap<Integer, Integer> cnt = new HashMap<>();
//        for (int num : nums) {
//            cnt.merge(num, 1, Integer::sum);
//        }
//        int ans = cnt.getOrDefault(1, 0);
//        for (int i = 2; i < n / 2; i++) {
//            for (int j = 1, k = 1; ; j <<= 1, k++) {
//                Long res = myPow(i, j);
//                if (res > Integer.MAX_VALUE) break;
//                else {
//                    if (cnt.get(res.intValue()) != null) {
//                        if (cnt.get(res.intValue()) == 1) {
//                            ans = Math.max(ans, (k - 1) * 2 + 1);
//                            break;
//                        }
//                    } else {
//                        ans = Math.max(ans, (k - 1) * 2) - 1;
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//    public long myPow(long x, int n) {
//        long N = n;
//        return N >= 0 ? quickMul(x, N) : 1 / quickMul(x, -N);
//    }
//
//    public long quickMul(long x, long N) {
//        if (N == 0) {
//            return 1;
//        }
//        long y = quickMul(x, N / 2);
//        return N % 2 == 0 ? y * y : y * y * x;
//    }

    public int maximumLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
        }
        for (int num : nums) {
            if (cnt.getOrDefault(num, 0) >= 2) {
                map.put(num, 1);
            }
        }
        Arrays.sort(nums);
        HashSet<Integer> set = new HashSet<>();
        int ans = cnt.getOrDefault(1, 0) % 2 == 0 ? cnt.getOrDefault(1, 0) - 1 : cnt.getOrDefault(1, 0);
        for (int i = cnt.getOrDefault(1, 0); i < n; i++) {
            if (set.contains(nums[i])) continue;
            double sqrt = Math.sqrt(nums[i]);
            if ((int) sqrt == sqrt) {
                Integer orDefault = map.getOrDefault((int)sqrt, 0);
                if (!set.contains(nums[i]) && map.containsKey(nums[i])) {
                    map.merge(nums[i], orDefault, Integer::sum);
                    set.add(nums[i]);
                }
                ans = Math.max(ans, orDefault * 2 + 1);
            }
        }
        return ans;
    }

    public long flowerGame(int n, int m) {
        long n1 = n / 2;
        long n2 = n / 2 + n % 2;
        long m1 = m / 2;
        long m2 = m / 2 + m % 2;
        return n1 * m2 + n2 * m1;
    }



}
