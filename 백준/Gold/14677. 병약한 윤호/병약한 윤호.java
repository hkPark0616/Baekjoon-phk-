import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static char[] arr;
    static int[][][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int len = 3 * N;
        
        arr = br.readLine().toCharArray();
        
        dp = new int[len][len][3];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(0, len - 1, 0));
    }

    static int dfs(int l, int r, int cnt) {
        if(l > r) return 0;
        if(dp[l][r][cnt] != -1) return dp[l][r][cnt];

        char now = "BLD".charAt(cnt);
        int result = 0;
        
        if(arr[l] == now) result = Math.max(result, dfs(l + 1, r, (cnt + 1) % 3) + 1); // 왼쪽 선택 + 현재 먹은 약

        if(arr[r] == now) result = Math.max(result, dfs(l, r - 1, (cnt + 1) % 3) + 1); // 오른쪽 선택 + 현재 먹은 약
        
        return dp[l][r][cnt] = result;
    }
}