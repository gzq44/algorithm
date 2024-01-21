package tec.diff;

import java.util.Locale;

/**
 * 2381
 *
 * @author gzq44
 * @date 2023/12/30 18:03
 **/
public class P2381 {

    public static void main(String[] args) {
        new P2381().shiftingLetters("abc", new int[][]{{0,1,0},{1,2,1},{0,2,1}});
    }

    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diff = new int[n];
        char[] arr = s.toCharArray();
        diff[0] = arr[0] - 'a';
        for (int i = 1; i < arr.length; i++) {
            diff[i] = (arr[i] - 'a') - (arr[i - 1] - 'a');
        }

        for (int[] shift : shifts) {
            int l = shift[0];
            int r = shift[1];
            int x = shift[2] == 0 ? -1 : 1;
            diff[l] += x;
            if (r + 1 < n) {
                diff[r + 1] -= x;
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            arr[i] = (char)((sum % 26 + 26) % 26 + 'a') ;
        }
        return new String(arr);
    }
}
