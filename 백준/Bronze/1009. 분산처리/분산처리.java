
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int T;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int a;
		int b;
		int ans;
		while (T-- >0) {
			
			// 컴퓨터 10대는 고정
			String [] nums  = br.readLine().split(" ");
			a = Integer.parseInt(nums[0]);
			b = Integer.parseInt(nums[1]);;;
			
			ans = a;
			for (int i=1;i<b;i++) {
				ans *= a;
				ans %= 10;
				
				//System.out.println(ans);
			}
			ans %= 10;
			if (ans==0) {
				ans = 10;
			}
			
			
			sb.append(ans).append("\n");
			
			
		}
		System.out.println(sb);
		
		
	}

}
