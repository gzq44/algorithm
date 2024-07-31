import java.util.*;

/**
 * shit
 *
 * @author gzq44
 * @date 2023/08/08 21:04
 **/
public class LeetCodeMain {

    public static void main(String[] args) {
        System.out.println(new LeetCodeMain().pow(2, 11, Integer.MAX_VALUE));
    }

    private int pow(int x, int n, int mod) {
        int res = 1;
        while (n > 0) {
            if (n % 2 > 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n /= 2;
        }
        return res;
    }


}

