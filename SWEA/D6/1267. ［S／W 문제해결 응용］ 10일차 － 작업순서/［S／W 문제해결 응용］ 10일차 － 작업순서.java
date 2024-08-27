import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int V, E, indegree[];
	static List<List<Integer>> graph = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int t = 1; t <= 10; t++) {
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// 진입차수
			indegree = new int[V + 1];
			
			graph = new ArrayList<>();
			
			for(int i = 0; i <= V; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b); // a -> b
				indegree[b] += 1; // 진입차수 + 1
			}
			
			sb.append("#" + t + " ");
			topologySort();
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void topologySort() {
		List<Integer> result = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		
		// 진입 차수가 0인 노드 큐에 삽입
		for(int i = 1; i <= V; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			// 현재 노드번호
			int temp = queue.poll();
			result.add(temp);

			// 꺼낸 노드의 인접한 노드
			List<Integer> list = graph.get(temp);

			// 인접 노드 개수만큼 수행
			for(int i = 0; i < list.size(); i++) {
				// 큐에서 진입 차수가 0인 정점을 꺼내어 자신과 인접한 정점의 간선을 제거
				// 인접 노드 진입차수 1 감소
				indegree[list.get(i)]--;
				
				// 간선 제거 후 진입 차수가 9이 된 정점을 큐에 넣음
				if(indegree[list.get(i)] == 0) {
					queue.offer(list.get(i));
				}
				
			}
		}
		
		
		for(int i = 0; i < result.size(); i++) {
			sb.append(result.get(i) + " ");
		}
	}

}
