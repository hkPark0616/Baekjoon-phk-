import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, T;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        dp = new int[T + 1]; // 총 j시간까지 사용했을 때 얻을 수 있는 최대 점수
        arr = new int[N + 1][2]; // 과목 점

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        knapsack();

        System.out.println(dp[T]);
    }

    static void knapsack() {
        for(int i = 1; i <= N; i++) {
            int time = arr[i][0];
            int score = arr[i][1];
            for(int j = T; j >= time; j--) {
                // 과목 i를 선택하기 전
                // 현재 총시간 j에서, 과목 i를 포함한다면, 그 과목이 차지하는 시간 time만큼 이전 상태는 j - time
                // 둘 중 큰 값
                dp[j] = Math.max(dp[j], dp[j - time] + score);
            }
        }
    }
}