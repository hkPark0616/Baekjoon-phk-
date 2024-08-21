import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, arr[][];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		divide(0, 0, N);

		System.out.println(sb);
	}
	
	static void divide(int r, int c, int size) {
		
		int sum = 0;
		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				sum += arr[i][j];
			}
		}
		
		if(sum == 0) {
			sb.append("0");
		}else if(sum == size * size) {
			sb.append("1");
		}else {
			int half = size / 2;
			sb.append("(");
			divide(r, c, half);
			divide(r, c + half, half);
			divide(r + half, c, half);
			divide(r + half, c + half, half);
			sb.append(")");
		}
	}
}