import java.util.*;

class Solution {
    Node[] nodes;
    List<Integer> preorderList = new ArrayList<>();
    List<Integer> postorderList = new ArrayList<>();
    class Node{
        int x, y, idx;
        Node left, right;

        public Node(int x, int y, int idx){
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        int N = nodeinfo.length;
        nodes = new Node[N];
        
        for(int i = 0; i < N; i++) nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        
        // y 내림차순, x 오름차순
        Arrays.sort(nodes, (a, b) -> {
            if (a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        });
        
        Node root = nodes[0];
        for(int i = 1; i < N; i++) insertNode(root, nodes[i]);
        
        preorder(root);
        postorder(root);
        
        answer = new int[][] {
            preorderList.stream().mapToInt(i -> i).toArray(),
            postorderList.stream().mapToInt(i -> i).toArray()
        };
        
        return answer;
    }
    
    void insertNode(Node parent, Node child) {
        if(child.x < parent.x) {
            if(parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else {
            if(parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }
    
    void preorder(Node node){
        if(node == null) return;
        preorderList.add(node.idx);
        preorder(node.left);
        preorder(node.right);
    }
    
    void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        postorderList.add(node.idx);
    }
}