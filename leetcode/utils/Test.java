package utils;

import java.util.Arrays;

public class Test {
    int N = (int)1e4+10, M = 4 * N;
    int idx;
    int[] he = new int[N], e = new int[M], ne = new int[M];
    int[] cnts = new int[N];
    void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }
    public static void main(String[] args) {
        int[][] ints = {{1,3,4}, {2,3,5}, {3,1,5}, {4}, {5}};
        new Test().axss(ints);
    }

    void axss(int[][] arr) {
        Arrays.fill(he, -1);
        for (int i = 0; i < arr.length; i++) {
            for (int j : arr[i]) {
                add(i, j);
            }
        }
        for (int i = he[0]; i != -1; i = ne[i]) {
            int j = e[i];
            System.out.println(j);
        }

    }
}
