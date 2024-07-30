import java.util.*;

public class Main{
	public static int N, M;
	public static int arr[];
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();	
		
		arr = new int[M];
		
		dfs(1, 0);
	}
	
	public static void dfs(int a, int d) {
		if(d == M) {
			for(int i: arr) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		for(int i = a; i <= N; i++) {
			arr[d] = i;
			
			dfs(a++, d + 1);
		}
	}
}