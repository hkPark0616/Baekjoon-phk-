import java.util.*;

class Solution {
    int[] parent;
    class Node implements Comparable<Node> {
        int to, weight;
        
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        for(int[] path: paths) {
            graph.get(path[0]).add(new Node(path[1], path[2]));
            graph.get(path[1]).add(new Node(path[0], path[2]));
        }
        
        boolean[] isGate = new boolean[n+1];
        boolean[] isSummit = new boolean[n+1];
        for (int g : gates) isGate[g] = true;
        for (int s : summits) isSummit[s] = true;
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int g: gates) {
            dist[g] = 0;
            pq.offer(new Node(g, 0));
        }
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(dist[cur.to] < cur.weight) continue;
            if(isSummit[cur.to]) continue; // 산봉우리 휴식
            
            for(Node next: graph.get(cur.to)) {
                if(isGate[next.to]) continue;
                int intensity = Math.max(cur.weight, next.weight);
                
                if(dist[next.to] > intensity) {
                    dist[next.to] = intensity;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
            
        }
        
        Arrays.sort(summits); // 산봉우리의 번호 낮은 순 정렬
        int minIntensity = Integer.MAX_VALUE;
        int summitNum = 0;
        for(int s: summits) {
            if(dist[s] < minIntensity) {
                minIntensity = dist[s];
                summitNum = s;
            }
        }
        
        answer = new int[] {summitNum, minIntensity};
        return answer;
    }
}