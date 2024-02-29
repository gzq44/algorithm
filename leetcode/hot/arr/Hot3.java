package hot.arr;

/**
 * 3
 *
 * @author gzq44
 * @date 2024/02/13 17:42
 **/
public class Hot3 {
    public static void main(String[] args) {
        new Hot3().removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
    }
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[p] || i == 0) {
                nums[p] = nums[i];
                p++;
            }
        }
        return p;
    }
}
