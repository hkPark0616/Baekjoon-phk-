import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

 class Node implements Comparable<Node>{
	int to;
	int from;
	int weight;
	
	public Node(int to, int from, int weight) {
		this.to = to;
		this.from = from;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node node) {
		return weight - node.weight;
	}
}

public class Solution {
	static int V, E;
	static int[] parent;
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			pq = new PriorityQueue<>();
			
			parent = new int[V + 1];
			for(int i = 0; i <= V; i++) {
				parent[i] = i;
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				pq.add(new Node(a, b, w));
			}
			
			int size = pq.size();

			long sum = 0;
			for(int i = 0; i < size; i++) {
				Node node = pq.poll();
		
				if(!isSame(node.to, node.from)) {
					sum += node.weight;
					union(node.to, node.from);
				}
			}
			
			System.out.println("#" + t + " " + sum);
		}

	}
	
	static void union(int a, int b) {
		int aRoot = parent[a];
		int bRoot = parent[b];
		
		if(aRoot != bRoot) {
			parent[bRoot] = aRoot;
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
