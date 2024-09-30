import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, app[][];
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        app = new int[N + 1][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            app[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            app[i][1] = Integer.parseInt(st.nextToken());
        }

        knapsack();
    }

    static void knapsack(){
        int[][] dp = new int[N + 1][10001];

        for (int i = 1; i <= N; i++) {
            int mem = app[i][0];
            int cost = app[i][1];

            for (int j = 0; j < 10001; j++) {

                if(j >= cost) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + mem);
                }else{
                    // 선택 x
                    dp[i][j] = dp[i - 1][j];
                }

                if(dp[i][j] >= M){
                    answer = Math.min(answer, j);
                }
            }
        }

        System.out.println(answer);
    }
}
