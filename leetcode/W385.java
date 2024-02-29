import apriceshittest.Main;
import apriceshittest.Trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 385
 *
 * @author gzq44
 * @date 2024/02/19 21:49
 **/
public class W385 {

    public long countPrefixSuffixPairs(String[] words) {
        Node node = new Node();
        int n = words.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int m = words[i].length();
            char[] arr = words[i].toCharArray();
            Node root = node;
            for (int j = 0; j < m; j++) {
                int p = (arr[j] - 'a') << 5 | arr[n - 1 - j];
                root = node.son.computeIfAbsent(p, k -> new Node());
                ans += root.cnt;
            }
            root.cnt++;
        }
        return ans;
    }

    public static void main(String[] args) {
        new W385().mostFrequentPrime(new int[][]{{1,1},{9,9},{1,1}});
    }
    int[][] dirs = {{1, 0},
                    {1, 1},
                    {0, 1},
                    {-1, 1},
                    {-1, 0},
                    {-1, -1},
                    {0, -1},
                    {1, -1}};

    public int mostFrequentPrime(int[][] mat) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = -1;
        int cnt = 0;
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int[] d : dirs) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(mat[i][j]);
                    int x = i;
                    int y = j;
                    while (x >= 0 && x < n && y >=0 && y < m) {
                        x = x + d[0];
                        y = y + d[1];
                        if (x >= 0 && x < n && y >=0 && y < m) {
                            sb.append(mat[x][y]);
                            Integer k = Integer.valueOf(sb.toString());
                            if (isP(k)) {
                                map.merge(k, 1, Integer :: sum);
                                if (map.get(k) > cnt || (map.get(k) == cnt && k > ans)) {
                                    ans = k;
                                    cnt = map.get(k);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    boolean isP(int p) {
        for (int i = 2; i * i <= p; i++) {
            if (p % i == 0) return false;
        }
        return true;
    }
}

class Node {
    Map<Integer, Node> son = new HashMap<>();
    int cnt;
}

