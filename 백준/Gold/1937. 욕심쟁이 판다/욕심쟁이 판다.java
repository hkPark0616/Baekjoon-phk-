import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n;
    static int[][] map, dp;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dfs(i, j);
            }
        }

        int answer = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.println(answer);
    }

    static int dfs(int x, int y) {
        
        if(dp[x][y] != 0) return dp[x][y];

        for(int[] delta: deltas) {
            int nx = x + delta[0];
            int ny = y + delta[1];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny));
            }
        }

        return dp[x][y] += 1;
    }
}