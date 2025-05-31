import java.util.*;

class Solution {
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(!visited[i]) {
                bfs(i, n, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    static void bfs(int s, int n, int[][] computers) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(s);
        visited[s] = true;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for(int i = 0; i < n; i++) {
                if(!visited[i] && computers[cur][i] == 1) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}