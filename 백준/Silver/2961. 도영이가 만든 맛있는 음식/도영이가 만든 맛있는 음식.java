import java.util.Scanner;

public class Main {
	public static int N, S, B, answer;
	public static int arr[][];
	public static boolean visit[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N][2];
		visit = new boolean[N];
		
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			S = sc.nextInt(); // 곱
			B = sc.nextInt(); // 합
			arr[i][0] = S;
			arr[i][1] = B;	
		}                    
		
		cook(0, 1, 0);
		
		System.out.println(answer);
	}
	
	public static void cook(int cnt, int s, int b) {
		if(cnt == N) {
			if(b !=0) {
				int diff = Math.abs(s - b);
				answer = Math.min(diff, answer);
			}
			return;
		}

		visit[cnt] = true;
		cook(cnt + 1, s * arr[cnt][0], b + arr[cnt][1]);
		visit[cnt] = false;
		cook(cnt + 1, s, b);
	}

}