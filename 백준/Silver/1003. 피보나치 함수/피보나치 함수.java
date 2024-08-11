import java.util.*;

public class Main {
	public static int dp[][];
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			int N = sc.nextInt();
			dp = new int[N + 3][2];
			dp[0] = new int[] {1, 0};
			dp[1] = new int[] {0, 1};
			
			for(int i = 0; i < N; i++) {
				fibonacci(N, dp);
			}
			
			System.out.println(dp[N][0] + " " + dp[N][1]);
			
		}
		
	}
	
	public static void fibonacci(int n, int dp[][]) {
	    
		if(dp[n][0] != 0 || dp[n][1] != 0) {
			return;
		}
		
		fibonacci(n - 1, dp);
		fibonacci(n - 2, dp);
	
		dp[n][0] = dp[n - 1][0] + dp[n - 2][0];
		dp[n][1] = dp[n - 1][1] + dp[n - 2][1];
	}
	
}
