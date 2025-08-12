class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        
        // 차분배열
        int[][] diff = new int[N + 1][M + 1];
        
        for(int[] s: skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            
            int val = (type == 1) ? -degree : degree;
            
            diff[r1][c1] += val;
            diff[r1][c2 + 1] -= val;
            diff[r2 + 1][c1] -= val;
            diff[r2 + 1][c2 + 1] += val;
        }
        
        // 가로
        for(int i = 0; i < N; i++) {
            for(int j = 1; j < M; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }
        
        // 세로
        for(int j = 0; j < M; j++) {
            for(int i = 1; i < N; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }
        
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int hp = board[i][j] + diff[i][j];
                if(hp > 0) answer++;
            }
        }
        return answer;
    }
}