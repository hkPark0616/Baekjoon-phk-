import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int[] dp = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            int answer = 0;
            for(int i = 0; i < N; i++){
                int children =  Integer.parseInt(st.nextToken());
                // 일반 LIS로 하면 시간초과가...
                // 현재 원소가 이전 원소보다 크고, 가장 긴 증가하는 부분 수열을 확장할 수 있다면, 그 수열을 길이를 1 증가시켜서 갱신
                dp[children] = dp[children - 1] + 1;
                answer = Math.max(answer, dp[children]);
            }

            System.out.println("#" + t + " " + (N - answer));
        }
    }
}