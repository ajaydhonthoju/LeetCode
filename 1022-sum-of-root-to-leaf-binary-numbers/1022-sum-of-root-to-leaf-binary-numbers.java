/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans = 0;

    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        List<Integer> path = new ArrayList<>();
        getPath(root, path);
        return ans;
    }

    private void getPath(TreeNode root, List<Integer> path) {
        if (root == null) return;

        path.add(root.val);

        if (root.left == null && root.right == null) {
            ans += getDecimal(path);
        }

        getPath(root.left, path);
        getPath(root.right, path);

        path.remove(path.size() - 1);
    }

    private int getDecimal(List<Integer> path) {
        int num = 0;
        for (int bit : path) {
            num = num * 2 + bit;
        }
        return num;
    }
}