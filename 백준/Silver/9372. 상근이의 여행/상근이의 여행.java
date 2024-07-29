import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			
			int answer = 1;
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int[][] arr = new int[M][2];
			
			for(int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				arr[i][0] = a;
				arr[i][1] = b;
			}
			
			answer = N - 1;
			
			System.out.println(answer);
		}
	}
}