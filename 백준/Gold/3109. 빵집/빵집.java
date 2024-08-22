import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, max;
	static char map[][];
	static boolean visited[][];
	static int delX[] = {-1, 0, 1}, delY[] = {1, 1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		max = 0;
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			if(dfs(i, 0)) {
				max++;
			}
		}

		System.out.println(max);
	}

	static boolean dfs(int x, int y) {
		for(int i = 0; i < 3; i++) {
			int nx = x + delX[i];
			int ny = y + delY[i];

			if(nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] == '.' && !visited[nx][ny]) {
				visited[nx][ny] = true;
				
				if(ny == C - 1) return true;
				
				if(dfs(nx, ny)) return true;
			}
		}
		return false;
	}
}
