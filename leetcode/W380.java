import java.util.ArrayList;
import java.util.List;

/**
 * 2
 *
 * @author gzq44
 * @date 2024/01/14 10:30
 **/
public class W380 {

//    public long findMaximumNumber(long k, int x) {
//        int ans = 0;
//
//    }

    public static void main(String[] args) {
        new W380().beautifulIndices("frtzggff", "g", "f", 1);
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> aList = search(s, a);
        List<Integer> bList = search(s, b);
        int n = aList.size();
        int m = bList.size();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < n && j < m; ) {
            if (Math.abs(aList.get(i) - bList.get(j)) <= k) {
                ans.add(aList.get(i));
                i++;
                continue;
            }
            if (aList.get(i) < bList.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return ans;
    }
    int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLengths[maxLength - 1]; // ①
            }
            if (pattern.charAt(maxLength) == pattern.charAt(i)) {
                maxLength++; // ②
            }
            maxMatchLengths[i] = maxLength;
        }
        return maxMatchLengths;
    }

    List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count - 1];
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count++;
            }
            if (count == pattern.length()) {
                positions.add(i - pattern.length() + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }
}
