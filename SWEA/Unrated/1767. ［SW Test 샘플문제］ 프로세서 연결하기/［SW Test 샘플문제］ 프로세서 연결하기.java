import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, cells[][], maxCoreCnt, minWireLength;
	static List<int[]> cores;
	static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cells = new int[N][N];
			cores = new ArrayList<>();
			
			maxCoreCnt = 0;
			minWireLength = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
					if(cells[i][j] == 1) {
//						if(i != 0 && i != N - 1 && j != 0 && j != N - 1) {
							cores.add(new int[] {i, j});
//						}
						
					}
				}
			}

			core(0, 0, 0);
			
			System.out.println("#" + t + " " + minWireLength);
		}
	}
	
	static void core(int idx, int connectedCores, int wireLength) {
		if(idx == cores.size()) {
			if(connectedCores > maxCoreCnt) {
				maxCoreCnt = connectedCores;
				minWireLength = wireLength;
			}else if (connectedCores == maxCoreCnt) {
				minWireLength = Math.min(minWireLength, wireLength);
		    }
			
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(possible(i, idx)) {
				int len = connect(i, idx);
				core(idx + 1, connectedCores + 1, wireLength + len);
				disconnect(i, idx);
			}
		}
		core(idx + 1, connectedCores, wireLength);
		

	}
	static boolean possible(int d, int idx) {
		int[] core = cores.get(idx);
		int nx = core[0];
		int ny = core[1];
		boolean flag = true;
		
		while(true) {
			nx += delX[d];
			ny += delY[d];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
			
			if(cells[nx][ny] != 0) {
				flag = false;
				break;
			}
		}
		
		return flag;		
	}
	
	static int connect(int d, int idx) {
		int len = 0;
		int[] core = cores.get(idx);
		int nx = core[0];
		int ny = core[1];
		
		while(true) {
			nx += delX[d];
			ny += delY[d];

			if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
			
			cells[nx][ny] = 2;
			len++;
					
		}
		return len;
	}
	
	static void disconnect(int d, int idx) {
		int[] core = cores.get(idx);
		int nx = core[0];
		int ny = core[1];
		
		while(true) {			
			nx += delX[d];
			ny += delY[d];

			if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;
			
			cells[nx][ny] = 0;
		}
	}
	
	static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(cells[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}