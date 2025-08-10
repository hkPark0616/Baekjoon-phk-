import java.util.*;

class Solution {
    static int N;
    static int[][][] cost;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        N = board.length;
        cost = new int[N][N][4];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        
        bfs(board);
        
        for(int d = 0; d < 4; d++) answer = Math.min(answer, cost[N - 1][N - 1][d]);
        
        return answer;
    }
    
    static void bfs(int[][] board) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[3], b[3]));
        for(int dir = 0; dir < 4; dir++) {
            int nx = deltas[dir][0];
            int ny = deltas[dir][1];
            
            if(nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == 0) {
                pq.offer(new int[] {nx, ny, dir, 100});
                cost[nx][ny][dir] = 100;
            }
        }
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int c = cur[3];
            
            if(c > cost[x][y][dir]) continue;
            
            for(int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];
                
                if(nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] != 1) {
                   int addCost = (d == dir) ? 100 : 600;
                   int nextCost = c + addCost;
                    
                    if(cost[nx][ny][d] > nextCost) {
                        cost[nx][ny][d] = nextCost;
                        pq.offer(new int[] {nx, ny, d, nextCost});
                    }
                }
            }
        }
    }
}