import java.util.*;

public class Main{
	
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		int answer = 0;
		
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			int n = sc.nextInt();
			
			int[] arr = new int[n + 1 + 3];
			
			arr[1] = 1;
			arr[2] = 2;
			arr[3] = 4;
			
			for(int i = 4; i <= n; i++) {
				arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
			}
			
			System.out.println(arr[n]);
		}
	}
}