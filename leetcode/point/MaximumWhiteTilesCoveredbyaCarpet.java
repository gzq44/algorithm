package point;

import java.util.Arrays;

/**
 * 2271
 *
 * @author gzq44
 * @date 2023/08/02 23:03
 **/
public class MaximumWhiteTilesCoveredbyaCarpet {

    public static void main(String[] args) {
        new MaximumWhiteTilesCoveredbyaCarpet().maximumWhiteTiles(new int[][]{{1,2},{3,4}}, 1);
    }

    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int cnt = 0;
        int n = tiles.length;
        int left = 0;
        int ans = 0;
        Arrays.sort(tiles, ((o1, o2) -> {
            return o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0];
        }));
        for (int right = 0; right < n; right++) {
            cnt += tiles[right][1] - tiles[right][0] + 1;
            if (tiles[right][1] - carpetLen < tiles[left][0]) {
                ans = Math.max(ans, cnt);
                continue;
            }
            while (tiles[right][1] - carpetLen >= tiles[left][1]) {
                cnt -= (tiles[left][1] - tiles[left][0] + 1);
                left++;
            }
            if (tiles[right][1] - carpetLen < tiles[left][0]) {
                ans = Math.max(ans, cnt);
            } else {
                ans = Math.max(ans, cnt - tiles[right][1] + tiles[left][0] + carpetLen - 1);
            }
        }
        return ans;
    }
}
