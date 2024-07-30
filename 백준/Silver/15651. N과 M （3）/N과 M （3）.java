import java.util.*;

public class Main{
	public static int N, M;
	public static int arr[];
	public static StringBuilder sb;
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		sb = new StringBuilder();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[M];
		
		dfs(1, 0);
		System.out.println(sb);
		
	}
	
	public static void dfs(int a, int d) {
		if(d == M) {
			for(int i: arr) {
				sb.append(i + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = a; i <= N; i++) {
			arr[d] = i;
			
			dfs(a , d+1);
		}
	}
	
}
