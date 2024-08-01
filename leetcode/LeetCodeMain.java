import java.util.*;

/**
 * shit
 *
 * @author gzq44
 * @date 2023/08/08 21:04
 **/
public class LeetCodeMain {

    public static void main(String[] args) {
        System.out.println(new LeetCodeMain().maxmiumScore(new int[]{9,7,1,4,9}, 1));
    }

    public int maxmiumScore(int[] cards, int cnt) {
        int n = cards.length;
        Arrays.sort(cards);
        int sum = 0;
        int e1 = 1001;
        int o1 = 1001;
        int e2 = 1001;
        int o2 = 1001;
        for (int i = n - 1; i >= 0; i--) {
            int card = cards[i];
            if (i >= n - cnt) {
                sum += card;
                if (card % 2 == 0) e1 = Math.min(e1, card);
                else o1 = Math.min(o1, card);
            } else {
                if (card % 2 == 0 && e2 == 1001) e2 = card;
                else if (card % 2 != 0 && o2 == 1001) o2 = card;
            }
        }
        if (sum % 2 == 0) {
            return sum;
        }
        int mx = 0;
        if (e1 != 1001 && o2 != 1001) {
            mx = Math.max(mx, sum - e1 + o2);
        }
        if (e2 != 1001 && o1 != 1001) {
            mx = Math.max(mx, sum - o1 + e2);
        }
        return mx;
    }


}

