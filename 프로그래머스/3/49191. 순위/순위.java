class Solution {
    boolean[][] adj;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        adj = new boolean[n + 1][n + 1];
        
        for(int[] r: results) adj[r[0]][r[1]] = true;
        
        floyd(n);
        
        for(int i = 1; i <= n; i++) {
            int known = 0;
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                
                if(adj[i][j] || adj[j][i]) known++;
            }
            
            if(known == n - 1) answer++;
        }
        
        return answer;
    }
    
    void floyd(int n) {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(adj[i][k] && adj[k][j]) adj[i][j] = true;
                }
            }
        }
    }
}