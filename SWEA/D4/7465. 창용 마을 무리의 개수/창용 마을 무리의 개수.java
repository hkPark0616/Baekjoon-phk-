import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, answer;
	static int parent[];
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			answer = 0;
			
			sb.append("#" + t + " ");
			
			list = new ArrayList<>();
			
			parent = new int[N + 1];
			for(int i = 0; i <= N; i++) {
				parent[i] = i;
			}
			
			// 우선 입력된 값들끼리 union
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(isSame(a, b)){
					continue;
				}else {
					union(a, b);
				}
				
			}
			
			for(int i = 0; i <= N; i++) {
				if(list.contains(find(i))) {
					continue;
				}else {
					list.add(find(i));
				}
			}
			
			for(int i: list) {
				if(i != 0) answer++;
			}
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);

	}
	
	static void union(int a, int b) {
		a = parent[a];
		b = parent[b];
		
		if(a != b) {
			parent[b] = a;
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) return parent[a];
		
		// Path Compression
		return parent[a] = find(parent[a]);
	}
	
	static boolean isSame(int a, int b) {
		return find(a) == find(b);
	}

}
