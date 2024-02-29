package hot.window;

/**
 * 4
 *
 * @author gzq44
 * @date 2024/02/15 16:37
 **/
public class Hot4 {

    public static void main(String[] args) {
        new Hot4().minWindow("ADOBECODEBANC", "ABC");
    }

    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[] cnt = new int[100];
        int d = 0;
        for (int i = 0; i < m; i++) {
            if (cnt[t.charAt(i) - 'A']++ == 0) {
                d++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) cnt[i] = Integer.MIN_VALUE / 2;
        }
        int l = 0;
        int r = n + 1;
        int right = 0;
        for (int left = 0; left < n; left++) {
            int leftVal = s.charAt(left) - 'A';
            while (d > 0 && right < n) {
                int rightVal = s.charAt(right++) - 'A';
                if (--cnt[rightVal] == 0) {
                    d--;
                }
            }
            //子串必包含t所有字符数量
            if (right - left < r - l && d == 0) {
                r = right;
                l = left;
            }
            if (cnt[leftVal]++ == 0) d++;
        }
        return r > n ? "" : s.substring(l, r);
    }
}
