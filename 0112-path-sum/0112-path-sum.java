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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        // 리프 노드에 도착해서 남은 값과 확인
        if(root.left == null && root.right == null)
            return targetSum == root.val;
        
        // targetSum에서 남은 값을 줄여나가면서 확인
        int nextTargetSum = targetSum - root.val;

        // 두 방향 중 하나라도 조건을 만족하면 됨.
        return hasPathSum(root.left, nextTargetSum) || hasPathSum(root.right, nextTargetSum);
    }
}