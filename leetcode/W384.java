import java.util.ArrayList;
import java.util.List;

/**
 * 384
 *
 * @author gzq44
 * @date 2024/02/16 23:07
 **/
public class W384 {


    public static void main(String[] args) {
        new W384().countMatchingSubarrays(new int[]{1,2,3,4,5,6}, new int[]{1,1});
    }
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) nums[i] = 1;
            else if (nums[i] == nums[i + 1]) nums[i] = 0;
            else nums[i] = -1;
        }
        return searchByNum(nums, pattern).size();
    }

    List<Integer> searchByNum(int[] nums, int[] pattern) {
        List<Integer> positions = new ArrayList<>();
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            while (count > 0 && pattern[count] != nums[i]) {
                count = maxMatchLengths[count - 1];
            }
            if (pattern[count] == nums[i]) {
                count++;
            }
            if (count == pattern.length) {
                positions.add(i - pattern.length + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }
    int[] calculateMaxMatchLengths(int[] pattern) {
        int[] maxMatchLengths = new int[pattern.length];
        int maxLength = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (maxLength > 0 && pattern[maxLength] != pattern[i]) {
                maxLength = maxMatchLengths[maxLength - 1]; // ①
            }
            if (pattern[maxLength] == pattern[i]) {
                maxLength++; // ②
            }
            maxMatchLengths[i] = maxLength;
        }
        return maxMatchLengths;
    }
}
