import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, K;
    static int[][] dp;
    static List<List<int[]>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 도시 번호가 증가하는 순서대로
            if(a < b) graph.get(a).add(new int[] {b, c});
        }

        System.out.println(dfs(1, 1));
    }

    static int dfs(int cur, int cnt) {
        if(cnt > M) return Integer.MIN_VALUE;

        if(cur == N) return 0;

        if(dp[cur][cnt] != -1) return dp[cur][cnt];

        int result = Integer.MIN_VALUE;
        for(int[] arr: graph.get(cur)) {
            int next = arr[0];
            int score = arr[1];

            result = Math.max(result, score + dfs(next, cnt + 1));
        }
        
        
        return dp[cur][cnt] = result;
    }
}