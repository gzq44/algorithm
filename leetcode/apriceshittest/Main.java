package apriceshittest;


import org.omg.PortableInterceptor.INACTIVE;
import utils.TreeNode;

import java.util.*;

/**
 * shit
 *
 * @author gzq44
 * @date 2023/08/08 21:04
 **/
public class Main {

    public static void main(String[] args) {
        new Main().isHappy(19);
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = f(n);
        while (slow != fast) {
            slow = f(slow);
            fast = f(fast);
            if (fast == 1) return true;
            fast = f(fast);
            if (fast == 1) return true;
        }
        return fast == 1;
    }

    int f(int v) {
        int res = 0;
        while (v != 0) {
            res += (v % 10) * (v % 10);
            v /= 10;
        }
        return res;
    }
}

