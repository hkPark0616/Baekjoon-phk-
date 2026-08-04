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
    public List<List<Integer>> levelOrder(TreeNode root) {

        return bfs(root);
    }

    private List<List<Integer>> bfs(TreeNode node) {
        List<List<Integer>> result = new ArrayList<>();

        if(node == null) return result; 

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(node);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                TreeNode cur = q.poll();

                list.add(cur.val);

                if(cur.left != null) q.offer(cur.left);

                if(cur.right != null) q.offer(cur.right);
            }

            result.add(list);
        }

        return result;
    }
}