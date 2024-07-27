package template;

/**
 * 数组数组
 *
 * @author gzq44
 * @date 2024/03/05 23:36
 **/
class Fenwick {
    private final int[] tree;

    public Fenwick(int n) {
        tree = new int[n];
    }

    // 把下标为 i 的元素增加 1
    public void add(int i) {
        while (i < tree.length) {
            tree[i]++;
            i += i & -i;
        }
    }

    // 返回下标在 [1,i] 的元素之和
    public int pre(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i &= i - 1;
        }
        return res;
    }
}
