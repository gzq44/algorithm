package dfs;

import utils.TreeNode;

/**
 * 101
 *
 * @author gzq44
 * @date 2023/12/31 17:03
 **/
public class P101 {

    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    boolean isSameTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        boolean f1 = isSameTree(left.left, right.right);
        boolean f2 = isSameTree(left.right, right.left);
        return f1 && f2;
    }
}
