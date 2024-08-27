import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, arr[][], cnt;
	static boolean visited[][];
	static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		cnt = 1;
		bfs(0, 0);
		
		System.out.println(cnt);
	}
	
	static void bfs(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {a, b, cnt});
		
		visited[a][b] = true;

		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];		
			cnt = temp[2];
			
			if(x == N - 1 && y == M - 1) return;
			
			for(int d = 0; d < 4; d++) {
				int nx = x + delX[d];
				int ny = y + delY[d];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && arr[nx][ny] == 1) {
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny, cnt + 1});
				}
			}
		}
	}
}
