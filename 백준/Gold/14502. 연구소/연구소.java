import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][], answer;
	static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		answer = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
			}
		}
		
		wall(0);
		
		System.out.println(answer);
	}
	
	static void wall(int cnt) {
		if(cnt == 3) {
			int[][] copy = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
				}
			}
			spreadVirus(copy);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					wall(cnt + 1);
					map[i][j] = 0;
					
				}
			}
		}
	}
	
	static void spreadVirus(int[][] copyArr) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyArr[i][j] == 2) {
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[] {i, j});
					
					while(!queue.isEmpty()) {
						int[] temp = queue.poll();
						int x = temp[0];
						int y = temp[1];
						
						for(int d = 0; d < 4; d++) {
							int nx = x + delX[d];
							int ny = y + delY[d];
							
							if(nx >= 0 && nx < N && ny >= 0 && ny < M && copyArr[nx][ny] == 0) {
								copyArr[nx][ny] = 2;
								queue.offer(new int[] {nx, ny});
							}
						}
					}	
				}
			}
		}
		
		calcSafeArea(copyArr);
	}
	
	static void calcSafeArea(int[][] copyArr) {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyArr[i][j] == 0) cnt++;
			}
		}
		
		answer = Math.max(cnt, answer);
	}
}