import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		
		List<Integer> list = new ArrayList<>();
		int[] arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = arr[i - 1] + sc.nextInt();
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			sb.append(arr[y] - arr[x - 1] + "\n");
		}

		System.out.println(sb);

	}

}