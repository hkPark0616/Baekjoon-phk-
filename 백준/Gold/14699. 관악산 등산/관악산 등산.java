import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] arr, dp;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
 
            if(arr[a] < arr[b]) graph.get(a).add(b);
            else if(arr[a] > arr[b]) graph.get(b).add(a);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) sb.append(dfs(i)).append("\n");

        System.out.println(sb);
    }

    static int dfs(int cur) {
        if(dp[cur] != 0) return dp[cur];
        
        dp[cur] = 1;
        
        for(int next: graph.get(cur)) {
            dp[cur] = Math.max(dp[cur], dfs(next) + 1);
        }
        
        return dp[cur];
    }
}