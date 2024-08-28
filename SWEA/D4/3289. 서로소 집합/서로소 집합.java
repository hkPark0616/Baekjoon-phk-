import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, answer;
	static int parent[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			answer = 0;
			parent = new int[N + 1];
			for(int i = 0; i <= N; i++) {
				parent[i] = i;
			}
			
			sb.append("#" + t + " ");
			for(int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int g = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if(g == 0) {
					union(a, b);
				}else {
					if(isSame(a, b)) {
						answer = 1;
					}else {
						answer = 0;
					}
					sb.append(answer);
				}
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void union(int a, int b) {
		int a1 = find(a);
		int b1 = find(b);
		if(a1 != b1) {
			parent[b1] = a1;
		}
	}
	
	static int find(int a) {
		if(parent[a] == a) return parent[a];
		
		
		return parent[a] = find(parent[a]);
	}
	
	static boolean isSame(int a, int b) {
		return find(a) == find(b);
	}
}
