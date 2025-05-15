import java.util.*;

class Solution {
    static int[][] dp;
    static boolean[][] isPuddle;
    static final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, -1); // -1로 초기화: 아직 계산 안됨

        isPuddle = new boolean[n][m];
        for (int[] p : puddles) {
            int y = p[1] - 1;
            int x = p[0] - 1;
            isPuddle[y][x] = true;
        }

        return dfs(n - 1, m - 1); // 도착 지점에서 시작
    }

    static int dfs(int y, int x) {
        if (y < 0 || x < 0) return 0; // 범위 밖
        if (isPuddle[y][x]) return 0; // 물웅덩이
        if (y == 0 && x == 0) return 1; // 시작점 도착

        if (dp[y][x] != -1) return dp[y][x]; // 이미 계산됨

        // 왼쪽, 위쪽에서 오는 경우의 수 합
        int fromLeft = dfs(y, x - 1) % MOD;
        int fromUp = dfs(y - 1, x) % MOD;

        return dp[y][x] = (fromLeft + fromUp) % MOD;
    }
}
