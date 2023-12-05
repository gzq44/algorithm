package point;

/**
 * 2555
 *
 * @author gzq44
 * @date 2023/07/30 23:29
 **/
public class MaximizeWinFromTwoSegments {
    public static void main(String[] args) {
        new MaximizeWinFromTwoSegments().maximizeWin(new int[]{1,1,2,2,3,3,5}, 2);
    }
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int right = n - 1;

        int[] pre = new int[n + 1];
        int cnt = 0;
        int ans = 0;
        for (int left = right; left >= 0; left--) {
            cnt++;
            if (prizePositions[right] - prizePositions[left] < k) {
                continue;
            }
            while (prizePositions[right] - prizePositions[left] > k) {
                right--;
                cnt--;
            }
            if (prizePositions[right] - prizePositions[left] == k) {
                pre[left] = cnt - 1;
                ans = cnt + pre[right];
            }
        }
        return ans;
    }
}
