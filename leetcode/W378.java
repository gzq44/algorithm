/**
 * weekly
 *
 * @author gzq44
 * @date 2023/12/31 10:32
 **/
public class W378 {

    public boolean hasTrailingZeros(int[] nums) {
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 0) cnt++;
        }
        return cnt >= 2;
    }


    public static void main(String[] args) {
        new W378().maximumLength("bbbbabbb");
    }

    //prefix + binary
    public int maximumLength(String s) {
        int n = s.length();
        int[] diff = new int[n + 1];
        diff[1] = s.charAt(0) - 'a';
        for (int i = 1; i < n; i++) {
            diff[i + 1] = (s.charAt(i) - 'a') - (s.charAt(i - 1) - 'a');
        }


        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (!f(mid, s)) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left == 0 ? -1 : left;
    }

    boolean f(int m, String s) {
        int n = s.length();
        int cnt = 0;
        int[] arr = new int[26]; //窗户里的字母数量
        int[] res = new int[26]; //以字母为窗户长的串的数量
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (arr[idx]++ == 0) {
                cnt++;
            }
            if (i < m - 1) {
                continue;
            } else {
                if (cnt == 1) {
                    res[idx]++;
                }
            }
            if (--arr[s.charAt(i - m + 1) - 'a'] == 0) {
                cnt--;
            };
            if (res[idx] > 2) return true;
        }
        return false;
    }
}
