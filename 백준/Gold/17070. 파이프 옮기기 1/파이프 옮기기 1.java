import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;
    
    // 방향: 0 = 가로, 1 = 세로, 2 = 대각선
    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    static final int DIAGONAL = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(0, 1, 0));
    }

    static int dfs(int y, int x, int dir) {
        // 범위 밖이거나 벽이면 0
        if (y < 0 || x < 0 || y >= N || x >= N || map[y][x] == 1) return 0;

        if (y == N - 1 && x == N - 1) return 1; // 도착

        if (dp[y][x][dir] != -1) return dp[y][x][dir];

        int cnt = 0;

        // 가로일때
        if (dir == 0) {
            // → 가로
            if (x + 1 < N && map[y][x + 1] == 0)
                cnt += dfs(y, x + 1, 0);

            // ↘ 대각선
            if (x + 1 < N && y + 1 < N &&
                map[y][x + 1] == 0 &&
                map[y + 1][x] == 0 &&
                map[y + 1][x + 1] == 0)
                cnt += dfs(y + 1, x + 1, 2);
        } 
        // 세로일때
        else if (dir == 1) {
            // ↓ 세로
            if (y + 1 < N && map[y + 1][x] == 0)
                cnt += dfs(y + 1, x, 1);

            // ↘ 대각선
            if (x + 1 < N && y + 1 < N &&
                map[y][x + 1] == 0 &&
                map[y + 1][x] == 0 &&
                map[y + 1][x + 1] == 0)
                cnt += dfs(y + 1, x + 1, 2);
        } 
        // 대각선일떼
        else if (dir == 2) {
            // → 가로
            if (x + 1 < N && map[y][x + 1] == 0)
                cnt += dfs(y, x + 1, 0);

            // ↓ 세로
            if (y + 1 < N && map[y + 1][x] == 0)
                cnt += dfs(y + 1, x, 1);

            // ↘ 대각선
            if (x + 1 < N && y + 1 < N &&
                map[y][x + 1] == 0 &&
                map[y + 1][x] == 0 &&
                map[y + 1][x + 1] == 0)
                cnt += dfs(y + 1, x + 1, 2);
        }

        return dp[y][x][dir] = cnt;
    }
}
