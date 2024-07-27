package a.note;

/**
 * ss
 *
 * @author gzq44
 * @date 2023/12/16 16:59
 **/
public class NumArray {
    int[] num;
    int[] tree;
    int n;
    public NumArray(int[] nums) {
        n = nums.length;
        num = new int[n];
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(i, nums[i]);
        }
    }

    public void update(int index, int val) {
        int diff = val - num[index];
        num[index] = val;
        for (int i = index + 1; i < tree.length; i += i & -i) {
            tree[i] += diff;
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    private int prefixSum(int i) {
        int s = 0;
        for (; i > 0; i -= i & -i) {
            s += tree[i];
        }
        return s;
    }
//    private int[] nums;
//    private int[] tree;
//
//    public NumArray(int[] nums) {
//        int n = nums.length;
//        this.nums = new int[n]; // 使 update 中算出的 delta = nums[i]
//        tree = new int[n + 1];
//        for (int i = 0; i < n; i++) {
//            update(i, nums[i]);
//        }
//    }
//
//    public void update(int index, int val) {
//        int delta = val - nums[index];
//        nums[index] = val;
//        for (int i = index + 1; i < tree.length; i += i & -i) {
//            tree[i] += delta;
//        }
//    }
//
//    private int prefixSum(int i) {
//        int s = 0;
//        for (; i > 0; i &= i - 1) { // i -= i & -i 的另一种写法
//            s += tree[i];
//        }
//        return s;
//    }
//
//    public int sumRange(int left, int right) {
//        return prefixSum(right + 1) - prefixSum(left);
//    }
}
