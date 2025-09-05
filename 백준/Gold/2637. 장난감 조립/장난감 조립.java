import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] indegree;
    static int[][] arr, dp;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        indegree = new int[N + 1];
        arr = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];
        
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            graph.get(Y).add(X);
            indegree[X]++;

            arr[X][Y] = K; // X를 만드는 데 Y가 K개
        }

        topologySort();
    }

    static void topologySort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) { // 기본 부품
                q.offer(i);
                dp[i][i] = 1;
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            // cur: 현재 중간 부품
            // next: cur 사용해서 만든 상위 부품
            for(int next: graph.get(cur)) {
                int cnt = arr[next][cur]; // 다음 부품(next) 만들 때, 몇 개의 부품(cur)이 필요한지

                for(int i = 1; i <= N; i++) dp[next][i] += dp[cur][i] * cnt;
                
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }

        for(int i = 1; i <= N; i++) {
            if(dp[i][i] == 1) { // 기본 부품
                System.out.println(i + " " + dp[N][i]);
            }
        }
    }
}