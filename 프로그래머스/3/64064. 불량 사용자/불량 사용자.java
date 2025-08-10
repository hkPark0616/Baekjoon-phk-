import java.util.*;

class Solution {
    static int N, M;
    static boolean[] selected;
    static List<List<Integer>> users = new ArrayList<>();
    static Set<String> result;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        N = user_id.length;
        M = banned_id.length;
        
        selected = new boolean[N];
        users = new ArrayList<>();
        result = new HashSet<>();
        
        for(int i = 0; i < M; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < N; j++) {
                if(match(user_id[j], banned_id[i])) list.add(j);
            }
            
            users.add(list);
        }
        
        dfs(user_id, 0);
        
        answer = result.size();
        
        return answer;
    }
    
    static void dfs(String[] user_id, int idx) {
        if(idx == M) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                if(selected[i]) sb.append(user_id[i]).append(", ");
            }
            
            result.add(sb.toString());
            return;
        }
        
        for(int id: users.get(idx)) {
            if(!selected[id]) {
                selected[id] = true;
                dfs(user_id, idx + 1);
                selected[id] = false;
            }
        }
    }
    
    static boolean match(String userId, String bannedId) {
        if(userId.length() != bannedId.length()) return false;
        for(int i = 0; i < userId.length(); i++) {
            char c = bannedId.charAt(i);
            
            if(c == '*') continue;
            if(userId.charAt(i) != c) return false;
        }
        
        return true;
    }
}