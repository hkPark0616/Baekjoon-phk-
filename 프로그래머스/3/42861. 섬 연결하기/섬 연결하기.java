import java.util.*;

class Solution {
    static int V;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    
    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;
        
        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        V = n;
        parent = new int[V + 1];
        for(int i = 0; i <= V; i++) {
            parent[i] = i;
        }
        
        
        
        for(int[] cost: costs) {
            int from = cost[0];
            int to = cost[1];
            int weight = cost[2];
            
            pq.offer(new Node(from, to, weight));
        }
        
        int size = pq.size();
        for(int i = 0; i < size; i++) {
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int weight = node.weight;
            
            if(!isSame(from, to)) {
                answer += weight;
                union(from, to);
            }
        }
        
        return answer;
    }
    
    static void union(int a, int b) {
        a = parent[a];
        b = parent[b];
        
        if(a != b) {
            parent[b] = a;
        }
    }
    
    static int find(int a) {
        if(a == parent[a]) return parent[a];
        return parent[a] = find(parent[a]);
    }
    
    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
    
}