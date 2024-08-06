import java.lang.*;

class Solution {
    public static boolean visited[];
    public static int max;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        visited = new boolean[dungeons.length];
        max = 0;
        answer = 0;
        
        dfs(k, dungeons, 0);
        
        return max;
    }
    
    public static void dfs(int k, int[][] dungeons, int cnt){
        
        for(int i = 0; i < dungeons.length; i++){
            
            int visitF = dungeons[i][0];
            int recoverF = dungeons[i][1];
            
            if(visited[i] || k < visitF){
                continue;
            }
            
            visited[i] = true;
            
            dfs(k - recoverF, dungeons, cnt + 1);
            
            visited[i] = false;
        }
        
        max = Math.max(max, cnt);
    }
}