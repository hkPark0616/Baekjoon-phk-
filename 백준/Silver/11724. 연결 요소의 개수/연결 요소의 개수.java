import java.util.*;

public class Main {
	public static int n, m, u, v, cnt;
	public static int arr[][];
	public static boolean visit[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[1001][1001];
		visit = new boolean[1001];
		for(int i = 0; i < m; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			
			arr[u][v] = arr[v][u] = 1;
		}
		
		cnt = 0;
		
		for(int i = 1; i <= n; i++) {
			if(!visit[i]) {
				cnt++;
				dfs(i);
			}
		}
		
		System.out.println(cnt);
	}
	
	public static void dfs(int s) {
		if(visit[s]) return;
		visit[s] = true;

		for(int i = 1; i < arr.length; i++) {
			if(arr[i][s] == 1 && !visit[i]) {
				dfs(i);
			}
		}
	}
}
