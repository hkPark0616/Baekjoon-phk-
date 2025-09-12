import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[][] dp;
    static int[][] prev;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][10];
        prev = new int[N + 1][10];
        
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
            graph.add(new ArrayList<>());
        }
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j = 0; j < m; j++) {
                int a = Integer.parseInt(st.nextToken());
                graph.get(i).add(a);
            }
        }

        // 1일차부터
        int start = -1;
        for(int rice: graph.get(1)) {
            if(dfs(1, rice) == 1) {
                start = rice;
                break;
            }
        }

        if(start == -1) {
            System.out.println(-1);
            return;
        }

        
        StringBuilder sb = new StringBuilder();
        int cur = start;
        for (int day = 1; day <= N; day++) {
            sb.append(cur).append("\n");
            if (day < N) { // 마지막 날 전까지
                cur = getNext(day, cur); // 다음 날 선택된 떡
            }
        }

        System.out.println(sb);
    }

    // 일, 떡
    static int dfs(int day, int cur) {
        if(day == N) return 1; // 마지막 날

        if(dp[day][cur] != -1) return dp[day][cur];

        for(int next: graph.get(day + 1)) {
            if(next == cur) continue;

            if (dfs(day+1, next) == 1) {
                dp[day][cur] = 1;
                prev[day+1][next] = cur; // 다음 날 next를 고를 때, 전날(cur)을 기록
                return 1;
            }
            
        }
        
        return dp[day][cur] = 0;
    }

    static int getNext(int day, int cur) {
        // cur이 day + 1 기준 전날에 선택된 떡인지 확인
        for (int next : graph.get(day + 1)) {
            if (prev[day + 1][next] == cur) {
                return next;
            }
        }
        return -1;
    }
}