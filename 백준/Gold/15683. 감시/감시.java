import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][], copy[][], min;
	static int arr[];
	static List<int[]> cctvs;
	static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		
		map = new int[N][M];
		copy = new int[N][M];
		
		cctvs = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					cctvs.add(new int[] {i, j, map[i][j]});
				}
			}
		}
		
		arr = new int[cctvs.size()];
		
		perm(0, cctvs.size());
		
		System.out.println(min);
		
	}
	
	// cctv 방향 순열
	static void perm(int cnt, int R) {
		if(cnt == R) {
			
			// 맵 복사
			copy = new int[N][M];
			for(int i = 0; i < map.length; i++) {
				System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
			}
			
			// 생성된 방향 순열대로 방향 바꾸기 수행
			for(int i = 0; i < R; i++) {
				changeDirection(cctvs.get(i), arr[i]);
			}
			
			// 사각지대 구하기
			int blindspot = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(copy[i][j] == 0) {
						blindspot++;
					}
				}
			}
			
			min = Math.min(blindspot, min);
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			arr[cnt] = i;
			perm(cnt + 1, R);
		}
		
		
	}
	
	// cctv 방향 바꾸기
	static void changeDirection(int[] cctv, int direction) {
		int type = cctv[2];
		
		if(type == 1) {
			if(direction == 0) monitor(cctv, 0);
			
			else if(direction == 1) monitor(cctv, 1);
			
			else if(direction == 2) monitor(cctv, 2);
			
			else if(direction == 3) monitor(cctv, 3);
		}else if(type == 2) {
			if(direction == 0 || direction == 2) {
				monitor(cctv, 0);
				monitor(cctv, 2);
			}else {
				monitor(cctv, 1);
				monitor(cctv, 3);
			}
		}else if(type == 3) {
			if(direction == 0) {
				monitor(cctv, 0);
				monitor(cctv, 1);
			}else if(direction == 1) {
				monitor(cctv, 1);
				monitor(cctv, 2);
			}else if(direction == 2) {
				monitor(cctv, 2);
				monitor(cctv, 3);
			}else {
				monitor(cctv, 3);
				monitor(cctv, 0);
			}
			
		}else if(type == 4) {
			if(direction == 0) {
				monitor(cctv, 3);
				monitor(cctv, 0);
				monitor(cctv, 1);
			}else if(direction == 1) {
				monitor(cctv, 0);
				monitor(cctv, 1);
				monitor(cctv, 2);
			}else if(direction == 2) {
				monitor(cctv, 1);
				monitor(cctv, 2);
				monitor(cctv, 3);
			}else {
				monitor(cctv, 2);
				monitor(cctv, 3);
				monitor(cctv, 0);
			}
		}else if(type == 5) {
			monitor(cctv, 0);
			monitor(cctv, 1);
			monitor(cctv, 2);
			monitor(cctv, 3);
		}
	}
	
	// bfs
	static void monitor(int[] cctv, int direction) {
		boolean[][] watched = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {cctv[0], cctv[1]});
		watched[cctv[0]][cctv[1]] = true;
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			
			int nx = temp[0] + delX[direction];
			int ny = temp[1] + delY[direction];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || copy[nx][ny] == 6) break;
			
			if(copy[nx][ny] == 0) {
				copy[nx][ny] = -1;
				queue.offer(new int[] {nx ,ny});
			}else {
				queue.offer(new int[] {nx, ny});
			}
			
		}
	}
	
}