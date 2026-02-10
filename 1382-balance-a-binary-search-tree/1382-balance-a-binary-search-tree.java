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
    ArrayList<TreeNode> nodeList = new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        getNodesList(root);
        return BST(0 , nodeList.size() - 1);
    }

    TreeNode BST(int start,int end){
        if(start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode root = nodeList.get(mid);

        root.left = BST(start, mid - 1);
        root.right = BST(mid + 1, end);

        return root;
    }

    void getNodesList(TreeNode node){
        if(node == null) return;

        getNodesList(node.left);
        nodeList.add(node);   
        getNodesList(node.right);
    }
}