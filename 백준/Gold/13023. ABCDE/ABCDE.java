import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M,  answer, parent[];
	static boolean visited[];
	static List<List<Integer>> graph = new ArrayList<>();
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		visited = new boolean[N];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b); // a는 b의 친구다
			graph.get(b).add(a); // 양방향	
		}
		
		for(int i = 0; i < N; i++) {
			if(answer == 0) dfs(0, i);
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int cnt, int start) {
		if(cnt == 4) { // 조건에 맞는 A, B, C, D, E가 존재하면 answer = 1;
			answer = 1;
			return;
		}
		
		visited[start] = true;
		
		// start와 연결된 노드
		for(int i = 0; i < graph.get(start).size(); i++) {
			
			// 다음 노드
			int nextNode = graph.get(start).get(i);
			
			// 다음 노드 아직 방문 안했으면 방문함
			if(!visited[nextNode]) {
				dfs(cnt + 1, nextNode);
			}
		}
		
		visited[start] = false;
	}
}