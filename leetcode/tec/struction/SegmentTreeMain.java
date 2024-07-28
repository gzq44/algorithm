package tec.struction;

import java.util.ArrayList;
import java.util.List;

/**
 * 线段树题集
 *
 * @author gzq44
 * @date 2024/07/28 19:24
 **/
public class SegmentTreeMain {

    /**
     *
     *      699. 掉落的方块
     *      每次掉落后需要更新一次当前最大，区间查询 -> segment tree
     *      1、变量记录全局最大max
     *      2、区间查询最大
     *      3、区间更新值
     *
     *      thinking：能否实现呢
     *      1、区间累加值
     *      2、全范围查最大
     *
     */

    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        SegmentTree tree = new SegmentTree();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int[] p : positions) {
            int h = tree.query(p[0], p[0] + p[1]) + p[1];
            tree.modify(p[0], p[0] + p[1], h);
            ans.add(h);
        }
        return ans;
    }
}
