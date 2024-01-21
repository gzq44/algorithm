package days;

/**
 * 2645
 *
 * @author gzq44
 * @date 2024/01/11 22:38
 **/
public class P2645 {
    public static void main(String[] args) {
        new P2645().addMinimum("abc");
    }

    public int addMinimum(String word) {
        int n = word.length();
        int ans = 0;
        int idx = 0;
        for (int i = 0; i < n; idx = (idx + 1) % 3) {
            int cur = word.charAt(i) - 'a';
            if (cur == idx) i++;
            else ans++;
        }
        idx = (idx - 1 + 3) % 3;
        return ans + 2 - idx;
    }
}
