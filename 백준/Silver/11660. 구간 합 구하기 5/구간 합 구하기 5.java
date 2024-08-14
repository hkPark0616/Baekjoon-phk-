import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int arr[][] = new int[N + 1][N + 1];
		int arr2[] = new int[N * N + 1];
		/**
		 1 2 3 4
		 2 3 4 5
		 3 4 5 6
		 4 5 6 7	  
		*/
		int v = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				//int sum = arr[i][j-1] + sc.nextInt();
				
				arr[i][j] =  arr[i][j-1] + arr[i - 1][j] - arr[i - 1][j - 1] + sc.nextInt();
			}
		}
		
//		System.out.println();
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				//int sum = arr[i][j-1] + sc.nextInt();
//				
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		/**
		 2 2 3 4
		 3 4 3 4
		 1 1 4 4	  
		*/
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			// 42 10 6 1
			int sum = arr[x2][y2] - arr[x1 -1][y2] - arr[x2][y1 - 1] + arr[x1-1][y1-1];
	
			sb.append(sum + "\n");
		}
		  
		System.out.println(sb);

	}

}
