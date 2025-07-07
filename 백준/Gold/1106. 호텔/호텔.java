import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int C, N;
    static int[] dp;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
            
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][2];
        dp = new int[C + 100];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            arr[i][0] = cost;
            arr[i][1] = customer;
        }

        knapsack();

        int answer = Integer.MAX_VALUE;
        for (int i = C; i < dp.length; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }

    static void knapsack() {
        for(int i = 1; i <= N; i++) {
            int cost = arr[i][0];
            int customer = arr[i][1];
            for (int j = customer; j < dp.length; j++) {
                if (dp[j - customer] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - customer] + cost);
                }
            }
        }
    }
}