import java.util.*;

public class Main {
	public static int N, M, r, c, d;
	public static int [][] map;
	public static int cnt = 1;
	public static int[] delX = {-1, 0, 1, 0};
	public static int[] delY = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		clean(r, c, d);
		System.out.println(cnt);
		
	}
	
	public static void clean(int x, int y, int d) {

		map[x][y] = -1;
		
		boolean isClean = true;
		
		for(int i = 0; i < 4; i++) {
			int dx = x + delX[i];
			int dy = y + delY[i];
			if(dx >= 0 && dy >= 0 && dx < N && dy < M) {
				if(map[dx][dy] == 0) {
					isClean = false;
				}
			}
		}
		
		if(!isClean) {
			d = (d + 3) % 4;
			
			int rx = x + delX[d];
			int ry = y + delY[d];
			
			while(map[rx][ry] != 0) {
				d = (d + 3) % 4;
				rx = x + delX[d];
				ry = y + delY[d];
			}
			
			if(rx >= 0 && rx < N && ry >= 0 && ry < M && map[rx][ry] == 0) {
				map[rx][ry] = -1;
				cnt++;
				clean(rx, ry, d);
			}
		}else {
			int rx = x - delX[d];
			int ry = y - delY[d];
			
			if(rx >= 0 && rx < N && ry >= 0 && ry < M && map[rx][ry]  != 1) {
				clean(rx, ry, d);
			}
		}
	}
}
