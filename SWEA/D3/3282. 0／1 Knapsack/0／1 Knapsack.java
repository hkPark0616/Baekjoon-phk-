import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, K;
    static int[][] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N + 1][2];
            dp = new int[K + 1];

            for(int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                arr[i][0] = v;
                arr[i][1] = c;
            }

            knapsack();

            System.out.println("#" + t + " " + dp[K]);
        }
    }

    static void knapsack() {
        for(int i = 1; i <= N; i++) {
            int v = arr[i][0];
            int c = arr[i][1];
            for(int j = K; j >= v; j--){
                dp[j] = Math.max(dp[j], dp[j - v] + c);
            }
        }
    }
}