package hot.arr;

/**
 * 6
 *
 * @author gzq44
 * @date 2024/02/13 22:47
 **/
public class Hot6 {
    public static void main(String[] args) {
        new Hot6().rotate(new int[]{-1,-100,3,99}, 2);
    }

    public void rotate(int[] nums, int k) {
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int start = i;
            int pi = i;
            int pv = nums[i];
            int next = i + k;
            do {
                int tmp = nums[next % n];
                nums[next % n] = pv;
                pv = tmp;
                pi = next;
                next += k;
                cnt++;
                if (cnt == n) return;
            } while (start != (pi % n));
        }
    }
}
