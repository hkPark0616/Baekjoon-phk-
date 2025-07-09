import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;
    static long[][][] dp;

    // 0: 가로 / 1: 세로 / 2: 대각
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new long[N][N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 방향 가로
        dp[0][1][0] = 1;

        // 바텀업
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                if(map[y][x] == 1) continue;

                // 가로
                if(x - 1 >= 0) {
                     dp[y][x][0] += dp[y][x - 1][0] + dp[y][x - 1][2]; // 가로 or 대각
                }

                // 세로
                if(y - 1 >= 0) {
                    dp[y][x][1] += dp[y - 1][x][1] + dp[y - 1][x][2]; // 세로 or 대각
                }

                // 대각
                if(x - 1 >= 0 && y - 1 >= 0 && map[y - 1][x] == 0 && map[y][x - 1] == 0) {
                    dp[y][x][2] += dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2]; // 가로 or 세로 or 대각
                }
            }
        }

        long result = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];

        System.out.println(result);
    }
}