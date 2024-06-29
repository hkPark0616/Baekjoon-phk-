import java.util.Scanner;

public class Main {
	
	public static int dp[][] = new int[30][30];
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			// mCn
			int result = 1;
			for (int i = 1; i <= N; i++) {
				result = result * (M - i + 1) / i;
			}
			
			System.out.println(result);
			
			// dp
			//System.out.println(comb(M, N));
			
			
		}
		
	}
	
	// dp
	public static int comb(int n, int r) {
		if(dp[n][r] > 0) {
			return dp[n][r];
		}
		
		if(n == r || r == 0) {
			return dp[n][r] = 1;
		}
		
		return comb(n - 1, r - 1) + comb(n - 1, r);
	}

}