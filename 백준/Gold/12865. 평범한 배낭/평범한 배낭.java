import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][2];
        dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            arr[i][0] = W;
            arr[i][1] = V;
        }

        knapsack();

        System.out.println(dp[K]);
    }

    static void knapsack() {
        for (int i = 1; i <= N; i++) {
            int weight = arr[i][0];
            int value = arr[i][1];
            for (int j = K; j >= weight; j--) {
                // i 번째 아이템을 포함하지 않는 경우: dp[j], 값이 그대로 유지됨
                // i 번째 아이템을 포함하는 경우: dp][j - weight] + value
                // -> 현재 배낭의 용량이 j일 때, 현재 아이템을 포함할 수 있는 경우는 배낭의 남은 용량이 j - weight일 때
                // -> 즉, 현재 아이템을 포함시키기 위해서는 배낭의 용량이 최소한 weight 이상이어야 함
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }
    }
}