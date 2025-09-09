import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, K;
    static char[][] map;
    static int[][][] dp;
    static char[] word;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        word = br.readLine().toCharArray();

        dp = new int[N][M][word.length];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                 if(map[i][j] == word[0]) answer += dfs(i, j, 0);
            }
        }

        System.out.println(answer);
    }

    static int dfs(int x, int y, int idx) {
        if(idx == word.length - 1) return 1;
        
        if(dp[x][y][idx] != -1) return dp[x][y][idx];

        dp[x][y][idx] = 0;
        
        for(int[] delta: deltas) {
            for(int i = 1; i <= K; i++) {
                int nx = x + delta[0] * i;
                int ny = y + delta[1] * i;

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(map[nx][ny] == word[idx + 1]) dp[x][y][idx] += dfs(nx, ny, idx + 1);
            }
        }
        
        return dp[x][y][idx];
    }
}