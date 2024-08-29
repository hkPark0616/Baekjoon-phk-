import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer;
	static int[] parent;
	static PriorityQueue<Node> pq;
	
	static class Node implements Comparable<Node>{
		int from;
		int to;
		int weight;
		
		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node node) {
			return weight - node.weight;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(a, b, w));
		}
		
		answer = 0;
		int size = pq.size();
		for(int i = 0; i < size; i++) {
			Node node = pq.poll();
			int from = node.from;
			int to = node.to;
			if(!isSame(from, to)) {
				answer += node.weight;
				union(from, to);
			}
		}
		
		System.out.println(answer);
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
		
		return parent[a] = find(parent[a]);
	}
	
	static boolean isSame(int a, int b) {
		return find(a) == find(b);
	}
	
}