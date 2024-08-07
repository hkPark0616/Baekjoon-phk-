import java.util.*;

public class Main{
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] dp = new int[n + 1 + 2];
		
		dp[1] = 1;
		dp[2] = 3;
		
		for(int i = 3; i <= n % 10007; i++) {
			dp[i] = dp[i - 1] % 10007 + dp[i - 2] % 10007 + dp[i - 2] % 10007;
		}
		
		System.out.println(dp[n] % 10007);
	}
}