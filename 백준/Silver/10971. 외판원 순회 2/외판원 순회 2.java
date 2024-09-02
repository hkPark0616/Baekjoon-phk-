import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, map[][], answer;
	static boolean visited[];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		answer = Integer.MAX_VALUE;
		visited = new boolean[N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(1, i, i, 0);
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int cnt, int start, int prev, int cost) {
		if(cnt == N) {
			if(map[prev][start] != 0) {
				answer = Math.min(answer, cost + map[prev][start]);
			}
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			if(map[prev][i] == 0) continue;
			
			visited[i] = true;
			dfs(cnt + 1, start, i, cost + map[prev][i]);
			visited[i] = false;
		}
	}

}
