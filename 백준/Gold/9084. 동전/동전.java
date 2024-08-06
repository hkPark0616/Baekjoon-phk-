
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int t;
	static int N;
	static int [] coins;
	static int M;
	static int [] dp;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();;
		
		t= Integer.parseInt(br.readLine());
		
		while (t-- > 0) {			
			N = Integer.parseInt(br.readLine());
			coins = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i=0;i<N;i++ ) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());
			// data input
	
			
			dp = new int[M+1];
			dp[0] =1;
			for (int coin: coins) {
				
				for (int j=coin;j<M+1;j++) {
					dp[j] = dp[j] + dp[j-coin];
				}
			} // end dp
			sb.append(dp[M]).append("\n");
			
		} // end case
		System.out.println(sb);
	}

}
