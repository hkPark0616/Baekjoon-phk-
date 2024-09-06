import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, R, C, L, answer;
	static int map[][];
	static boolean visited[][];
	static int[] delX = {0, 0, -1, 1}, delY = {-1, 1, 0, 0};
	static int[][] pipes = {
	           {0, 0, 0, 0},
	            {1, 1, 1, 1},
	            {1, 1, 0, 0},
	            {0, 0, 1, 1},
	            {1, 0, 0, 1},
	            {0, 1, 0, 1},
	            {0, 1, 1, 0},
	            {1, 0, 1, 0},
											};
										
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// 맨홀 뚜겅 위치
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			// 탈출 후 소요된 시간
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			answer = 1;  // 맨홀 위치도 카운트
			
			bfs();
			
			System.out.println("#" + t + " " + answer);
			
		}	
	}
	
	static void bfs() {
	    Queue<int[]> queue = new LinkedList<>();
	    
	    queue.offer(new int[] {C, R});  // 시작 위치와 시간을 같이 저장
	    visited[R][C] = true;
	    
	    for(int t = 1; t < L; t++) {
	    	int size = queue.size();
	    	
	    	for(int i = 0; i < size; i++) {
		        int[] temp = queue.poll();
		        int x = temp[0];
		        int y = temp[1];
		   
		        int[] pipe = pipes[map[y][x]];

		        for (int d = 0; d < 4; d++) {
		            if (pipe[d] == 0) continue;

		            int nx = x + delX[d];
		            int ny = y + delY[d];
		            
		            int dir = d % 2 == 0 ? d + 1 : d - 1;  // 반대 방향 계산
		            
		            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

		            if (visited[ny][nx] || map[ny][nx] == 0) continue;
		 
	            	
		            if (pipes[map[ny][nx]][dir] == 1) {
		                queue.offer(new int[] {nx, ny});  // 다음 위치와 시간을 큐에 저장
		                visited[ny][nx] = true;
		                answer++;
		            }
		        }
		    }
	    }
	}
}