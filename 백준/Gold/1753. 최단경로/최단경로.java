import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E, K, dist[];
	static int INF = Integer.MAX_VALUE;
	static List<List<Node>> graph;
	
	static class Node implements Comparable<Node>{
		// 정점 번호, 가중치
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node node) {
			// TODO Auto-generated method stub
			return weight - node.weight;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		dist = new int[V + 1];
		Arrays.fill(dist, INF);

		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, w));
		}
		
		Dijkstra(K);

	}
	
	static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean visited[] = new boolean[V + 1];
		
		dist[start] = 0;
		
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int current = node.vertex;
			
			if(visited[current]) continue;
			
			visited[current] = true;
			
			List<Node> next = graph.get(current);
			
			for(int i = 0; i < next.size(); i++) {
				if(dist[next.get(i).vertex] > dist[current] + next.get(i).weight) {
					dist[next.get(i).vertex] = dist[current] + next.get(i).weight;
					pq.offer(new Node(next.get(i).vertex, dist[next.get(i).vertex]));
				}
			}
		}
		
		for(int i = 1; i < dist.length; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
	}

}
