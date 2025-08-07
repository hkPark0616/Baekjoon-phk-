import java.util.*;

class Solution {
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] dist;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        for(int[] road: roads) {
            int a = road[0];
            int b = road[1];
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        bfs(destination);
        
        answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
    
    static int bfs(int d) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(d);
        dist[d] = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int next: graph.get(cur)) {
                if(dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }
        return -1;
    }
}