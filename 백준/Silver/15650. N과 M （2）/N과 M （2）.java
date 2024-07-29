import java.util.*;

public class Main{
	public static int n, m;
	public static int[] arr;
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[m];
		
		dfs(1, 0);
		
	}
	
	public static void dfs(int a, int d) {
		if(d == m) {
			for(int i: arr) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = a; i <= n; i++) {
			arr[d] = i;
			
			dfs(i + 1, d + 1);
		}
		
	}
}