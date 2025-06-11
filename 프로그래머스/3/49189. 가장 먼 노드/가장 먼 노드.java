import java.util.*;

class Solution {
    static boolean[] visited;
    static int[] distance;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new boolean[n];
        distance = new int[n];
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] e: edge) {
            int a = e[0] - 1;
            int b = e[1] - 1;
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        bfs(0, n, edge);
        
        int max = 0;
        for (int d : distance) {
            if (d > max) max = d;
        }

        for (int d : distance) {
            if (d == max) answer++;
        }

        
        return answer;
    }
    
    static void bfs(int s, int n, int[][] edge) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(s);
        visited[s] = true;
        distance[s] = 0;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for(int next: graph.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[cur] + 1;
                    queue.offer(next);
                }
            }
           
        }
    }
}