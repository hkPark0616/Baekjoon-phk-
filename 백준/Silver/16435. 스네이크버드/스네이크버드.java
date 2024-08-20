import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M, arr[], answer;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		
		answer = M;
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N; i++) {
			if(arr[i] <= answer) answer++;
			
		}
		
		System.out.println(answer);

	}
}
