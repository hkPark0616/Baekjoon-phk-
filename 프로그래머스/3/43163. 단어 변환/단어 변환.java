import java.util.*;

class Solution {
    static class Pair {
        String word;
        int depth;

        Pair(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        answer = bfs(begin, target, words);
        
        return answer;
    }
    
    static int bfs(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(begin, 0));

        while(!q.isEmpty()) {
            Pair p = q.poll();
            String cur = p.word;
            int depth = p.depth;
            
            if(cur.equals(target)) {
                return depth;
            }
            
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && isOneCharDiff(cur, words[i])) {
                    visited[i] = true;
                    q.offer(new Pair(words[i], depth + 1));
                }
            }
        }
        
        return 0;
    }
    
    // 한 글자만 다른 경우 찾기
    static boolean isOneCharDiff(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
}