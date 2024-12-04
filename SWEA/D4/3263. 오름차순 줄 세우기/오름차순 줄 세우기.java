import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] dp = new int[N + 1];
            int answer = 0; // 오름차순으로 서는 가장 긴 부분 수열의 길이
    
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++){
                int children = Integer.parseInt(st.nextToken());

                // children 번호의 학생이 들어올 때까지의 오름차순 수열의 길이
                // 이전 번호 학생들의 수열 길이에 1을 더하여 현재 학생을 포함한 수열의 길이를 갱신
                dp[children] = dp[children - 1] + 1;
                // 가장 긴 오름차순 부분 수열의 길이 추적
                answer = Math.max(answer, dp[children]);
            }

            System.out.println("#" + t + " " + (N - answer));
        }
    }
}