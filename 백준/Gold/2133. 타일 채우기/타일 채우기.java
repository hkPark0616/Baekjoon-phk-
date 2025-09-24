import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if(N % 2 != 0) { // N이 홀수면 다 채울 수 없음
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];

        dp[0] = 1;
        dp[2] = 3;

        // i는 벽의 가로 길이
        for(int i = 4; i <= N; i++) {
            dp[i] = dp[i - 2] * 3; // 기본 패턴 3가지
            for(int j = 4; j <= i; j += 2) { 
                dp[i] += dp[i - j] * 2; // 특수 패턴 2가지
            }
        }

        System.out.println(dp[N]);
    }
}