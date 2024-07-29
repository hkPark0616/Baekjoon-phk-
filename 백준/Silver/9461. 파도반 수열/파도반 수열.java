import java.util.*;

public class Main{
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			long N = sc.nextLong();
			
			long[] dp = new long[(int)(N + 4)];
			
			dp[1] = 1;
			dp[2] = 1;
			dp[3] = 1;
			
			for(int i = 4; i <= N; i++) {
				dp[i] = dp[i - 3] + dp[i - 2];
			}
			
			sb.append(dp[(int)N] + "\n");
		}
		
		System.out.println(sb);
		
	}
}