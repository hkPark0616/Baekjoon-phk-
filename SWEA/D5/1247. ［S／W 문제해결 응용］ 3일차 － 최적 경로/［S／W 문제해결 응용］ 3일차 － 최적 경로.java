import java.io.*;
import java.util.*;

public class Solution {
	static int N, map[][], min;
	static int arr[];
	static boolean visited[];
	static int workX, workY, homeX, homeY;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][2];
			
			min = Integer.MAX_VALUE;
			
			arr = new int[N];
			visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			
			
			workX = Integer.parseInt(st.nextToken());
			workY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				map[i][0] = cx;
				map[i][1] = cy;
				
			}

			path(0);
			
			sb.append("#" + t + " " + min + "\n");

		}
		System.out.println(sb);
	}

	static void path(int cnt) {
		if(cnt == N) {
			int meet = Math.abs(workX - map[arr[0]][0]) + Math.abs(workY - map[arr[0]][1]);
			int gohome = Math.abs(homeX - map[arr[N - 1]][0]) + Math.abs(homeY - map[arr[N - 1]][1]);
			int sum = 0;
			for(int i = 0; i < N - 1; i++) {
				sum += (Math.abs(map[arr[i]][0] - map[arr[i + 1]][0]) + Math.abs(map[arr[i]][1] - map[arr[i + 1]][1]));
			}

			min = Math.min(min, sum + meet + gohome);
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			arr[cnt] = i;
			visited[i] = true;
			path(cnt + 1);
			visited[i] = false;
		}
	}
}