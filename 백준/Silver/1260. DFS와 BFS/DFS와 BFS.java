import java.util.*;

public class Main {
	
	public static StringBuilder sb = new StringBuilder();
	public static int arr[][];
	public static boolean visit[];
	public static int n, m, s;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		s = sc.nextInt();
		
		arr = new int[n + 1][n + 1];
		
		for(int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		visit = new boolean[n + 1];
		dfs(s);
		sb.append("\n");
		
		visit = new boolean[n + 1];
		bfs(s);
		
		System.out.println(sb);
		
	}
	
	public static void dfs(int s) {
		visit[s] = true;
		sb.append(s + " ");
		for(int i = 1; i < arr.length; i++) {
			if(!visit[i] && arr[s][i] == 1) {
				dfs(i);
			}
		}
	}
	
	public static void bfs(int s) {
		Queue<Integer> que = new LinkedList<>();
		que.add(s);
		visit[s] = true;
		sb.append(s + " ");
		
		while(!que.isEmpty()) {
			int n = que.poll(); 
			
			for(int i = 1; i < arr.length; i++) {
				if(arr[n][i] == 1 && !visit[i]) {
					visit[i] = true;
					sb.append(i + " ");
					que.add(i);
				}
			}
		}
	}

}
