package bfs;

/**
 * 1625
 *
 * @author gzq44
 * @date 2023/07/29 23:14
 **/
public class LexicographicallySmallestStringAfterApplyingOperations {

    public static void main(String[] args) {
        new LexicographicallySmallestStringAfterApplyingOperations().findLexSmallestString("74", 5, 1);
    }


    public String findLexSmallestString(String s, int a, int b) {
        //如果b是奇数则奇偶位都可以去排序最小
        int n = s.length();
        String ans = s;
        for (int i = 0; i < n; i++) {
            s = s.substring(b) + s.substring(0, b);
            char[] ca = s.toCharArray();
            for (int i2 = 0; i2 < 10; i2++) {
                //奇数位必定可以加
                for (int j = 1; j < n; j += 2) {
                    ca[j] = (char) ((ca[j] - '0' + a) % 10 + '0');
                }
                if ((b & 1) != 0) {
                    for (int i1 = 0; i1 < 10; i1++) {
                        for (int j = 0; j < n; j += 2) {
                            ca[j] = (char) ((ca[j] - '0' + a) % 10  + '0');
                        }
                        if (ans.compareTo(new String(ca)) > 0) {
                            ans = new String(ca);
                        }
                    }

                } else {
                    if (ans.compareTo(new String(ca)) > 0) {
                        ans = new String(ca);
                    }
                }
            }
        }
        return ans;
    }
}
