import java.util.*;

class Solution {
    static Map<String, PriorityQueue<String>> graph = new HashMap<>();
    static Deque<String> path = new ArrayDeque<>();
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }
        
        dfs("ICN");
        
        answer = path.toArray(new String[0]);
        
        return answer;
    }
    
    static void dfs(String cur) {
        PriorityQueue<String> pq = graph.get(cur);
        
        while(pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }
        
        path.addFirst(cur);
    }
    
    
}