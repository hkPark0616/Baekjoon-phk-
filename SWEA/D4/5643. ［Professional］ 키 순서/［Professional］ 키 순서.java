import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, count;
	static int[][] adjMatrix, radjMatrix;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			
			adjMatrix = new int[N + 1][N + 1];
			radjMatrix = new int[N + 1][N + 1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				radjMatrix[b][a] = adjMatrix[a][b] = 1;
				
			}
			
			// 각 학생마다 자신보다 큰, 작은 학생 각각 탐색
			int answer = 0;
			for(int i = 1; i <= N; i++) {
				count = 0;
				// bfs
//				if(gtBFS(i) + ltBFS(i) == N - 1) {
//					answer++;
//				}
				
				// dfs
//				gtDFS(i, new boolean[N + 1]);
//				ltDFS(i, new boolean[N + 1]);
//				if(count == N - 1) answer++;
				
				// dfs 최적화
				dfs(i, adjMatrix, new boolean[N + 1]);
				dfs(i, radjMatrix, new boolean[N + 1]);
				if(count == N - 1) answer++;
			}
			
			System.out.println("#" + t + " " + answer);
		}	
	}
	
	// 자신보다 큰 학생따라 탐색
	static int gtBFS(int start) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if(!visited[i] && adjMatrix[cur][i] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++; // 나보다 큰 사람 카운트
				}
			}
		}
		return cnt;
	}
	
	// 자신보다 작은 학생따라 탐색
	static int ltBFS(int start) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if(!visited[i] && adjMatrix[i][cur] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++; // 나보다 작은 사람 카운트
				}
			}
		}
		return cnt;
	}
	
	static void gtDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && adjMatrix[cur][i] != 0) {
				gtDFS(i, visited);
				count++;
			}
		}
	}
	
	static void ltDFS(int cur, boolean[] visited) {
		visited[cur] = true;
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && adjMatrix[i][cur] != 0) {
				ltDFS(i, visited);
				count++;
			}
		}
	}
	
	static void dfs(int cur, int[][] matrix, boolean[] visited) {
		visited[cur] = true;
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && matrix[cur][i] != 0) {
				dfs(i, matrix, visited);
				count++;
			}
		}
	}
}
