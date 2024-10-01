import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            int[] dp = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            for(int i = 0; i < N; i++) {
                dp[i] = 1;

                // arr[i]에 대해 이전 인덱스들에 있는 원소들과 비교
                // arr[j]까지의 부분수열에 arr[i]를 추가할 수 있다면
                // 갱신
                for(int j = 0; j < i; j++) {
                    if(arr[j] < arr[i]){
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                // 최장 길이 갱신
                max = Math.max(max, dp[i]);
            }

            System.out.println("#" + t + " " + max);
        }
    }
}