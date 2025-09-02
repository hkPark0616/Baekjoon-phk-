import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] train, trainSum;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        train = new int[N + 1];
        trainSum = new int[N + 1];

        st  = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            train[i] = Integer.parseInt(st.nextToken());    
            trainSum[i] = train[i] + trainSum[i - 1];
        }

        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1][4];
        // i번째 객차까지 고려했을 때, j대의 기관차로 운송할 수 있는 최대 승객 수
        for(int i = M; i <= N; i++) {
            for(int j = 1; j <= 3; j++) {
                // 1. 해당 객차(i)를 선택하지 않음 -> 이전 객차(i - 1)까지만 고려함
                // 2. 해당 객차를 선택함 -> i번째 객차를 끝으로 하는 M 길이의 객차 구간을 선택
                //    이전 구간(1 ~ i-M)에서 j-1대 기관차가 만든 최댓값
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - M][j - 1] + trainSum[i] - trainSum[i - M]);
            }
        }

        System.out.println(dp[N][3]);
    }
}