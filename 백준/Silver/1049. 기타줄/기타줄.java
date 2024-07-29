import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int arr[][] = new int[M][2];
		
		
		int answer = 0;
		
		for(int i = 0; i < M; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}	
		
		
		int setMin = arr[0][0];
		int min = arr[0][1];
		for(int i = 0; i < M; i++) {
			setMin = Math.min(arr[i][0], setMin);
			min = Math.min(arr[i][1], min);
		}	
		
		answer = Math.min(( (N / 6) + 1) * setMin, Math.min(((N / 6) * setMin) + ((N % 6) * min), min * N));

		
		System.out.println(answer);
		
	}
	
}