package thinking;

import java.util.List;

/**
 * 思维题集
 *
 * @author gzq44
 * @date 2024/07/28 16:04
 **/
public class Main {

    public static void main(String[] args) {
        new Main().numberOfSubstrings("001011001");
    }
    public int numberOfSubstrings(String s) {
        int n = s.length();
        // 最多枚举几个 0
        int lim = (int) Math.sqrt(n) + 2;

        // 计算下一个 0 的位置
        int[] nxt = new int[n + 1];
        nxt[n] = n;
        for (int i = n - 1; i >= 0; i--) {
            nxt[i] = nxt[i + 1];
            if (s.charAt(i) == '0') nxt[i] = i;
        }

        long ans = 0;
        // 枚举子串开头
        for (int i = 0; i < n; i++) {
            // 从左到右枚举子串里的 0，直到数量超出限制
            for (int j = i, cnt = (s.charAt(i) == '0' ? 1 : 0); j < n && cnt <= lim; j = nxt[j + 1], cnt++) {
                // 求出子串里 1 的数量
                int one = (nxt[j + 1] - i) - cnt;
                // 子串 s[i..nxt[j + 1] - 1] 必须是显著的，这一段里才有可能出现显著子串的右端点
                if (one >= 1L * cnt * cnt) {
                    // 看看子串右端点最多能左移几步
                    int t = one - cnt * cnt + 1;
                    // 和这一段的长度取 min
                    ans += Math.min(t, nxt[j + 1] - j);
                }
            }
        }
        return (int) ans;
    }

}
