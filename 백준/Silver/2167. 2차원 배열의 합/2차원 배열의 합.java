import java.util.*;

public class Main{
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int K = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		int answer = 0;;
		
		for(int k = 0; k < K; k++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			for(int a = i - 1; a < x; a++) {
				for(int b = j - 1; b < y; b++) {
					answer += arr[a][b];
				}
			}
			sb.append(answer + "\n");
			answer = 0;
		}
		
		System.out.println(sb);
	}
}