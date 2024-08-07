
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 14:51
// 17:26
//35분
public class Main {

	public static int T;
	public static int N;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		while (T-- >0) {
			
			
			
			N = Integer.parseInt(br.readLine());
			String[] numsString1 = br.readLine().split(" ");
			String[] numsString2 = br.readLine().split(" ");
			
			int[][] nums = new int[2][N];
			
			for (int i=0;i<N;i++) {
				nums[0][i] = Integer.parseInt(numsString1[i]);
				nums[1][i] = Integer.parseInt(numsString2[i]);
			}
			
			if (N==1) {
				System.out.println(Math.max(nums[0][0], nums[1][0]));
				continue;
			}
			// 각 숫자에 visited 처리해줘야 하겠지, 그래야 먹을 수 있는 지 확인이 되니까??
			// 아니지, 오히려 반대로, 날 뗄 수 있는 경우는 한정되어 있으니까 그중 맥스를 고르면 되겠다.
			
			// 선택 경우의 수
			// 1. 위에먹기- 아래거 먹기 
			//          - 안먹기
			//		    
			// 2. 아래먹기 - 위에거 먹기
			//			- 안먹기
			//
			// 3. 안먹는다 - 위에거 먹기
			// 			- 아래거 먹기
			// 			- 안먹기
			
			int [][] dp = new int [2][N];
			
			
			dp[0][0] = nums[0][0]; //50
			dp[1][0] = nums[1][0]; //30
			dp[0][1] = nums[1][0] + nums[0][1]; // 30 + 10; 40
			dp[1][1] = nums[0][0] + nums[1][1]; // 50 + 50; 100
			
			for (int i=2;i<N;i++) {
				dp[0][i] = Math.max(dp[1][i-1], Math.max(dp[0][i-2], dp[1][i-2])) + nums[0][i];
				dp[1][i] = Math.max(dp[0][i-1], Math.max(dp[0][i-2], dp[1][i-2])) + nums[1][i];
			}
			
			int ans = Math.max(dp[0][N-1], dp[1][N-1]);
			
			System.out.println(ans);
			
			
			
		}// end test
	
		
	}

}
