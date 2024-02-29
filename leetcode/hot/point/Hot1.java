package hot.point;

import hot.arr.Hot6;

/**
 * 1
 *
 * @author gzq44
 * @date 2024/02/14 13:08
 **/
public class Hot1 {
    public static void main(String[] args) {
        new Hot1().isPalindrome("A man, a plan, a canal: Panama");
    }
    public boolean isPalindrome(String s) {
        int n = s.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') c ^= 32;
            if (c >= 'a' && c <= 'z') sb.append(c);
            if (c >= '0' && c <= '9') sb.append(c);
        }
        s = sb.toString();
        n = s.length();
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
