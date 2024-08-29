import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static PriorityQueue<Node> pq;
	static int N;
	static double E, answer;
	static int[] arrX, arrY;
	static int[] parent;
	
	static class Node implements Comparable<Node>{
		int from;
		int to;
		double weight;
		
		public Node(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node node) {
			return Double.compare(weight, node.weight);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			pq = new PriorityQueue<>();
			
			arrX = new int[N];
			arrY = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arrX[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arrY[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());
			
			// 유클리드 거리
			for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double distance = Math.sqrt(Math.pow(arrX[i] - arrX[j], 2) + Math.pow(arrY[i] - arrY[j], 2));
                    pq.add(new Node(i, j, distance));
                }
            }
			
			
			parent = new int[N];
			for(int i = 0; i < N; i++) {
				parent[i] = i;
			}
			
			int size = pq.size();
			
			//
			BigDecimal answer = new BigDecimal(0);
			for(int i = 0; i < size; i++) {
				Node node = pq.poll();

				if(!isSame(node.to, node.from)) {
					BigDecimal val = new BigDecimal(E * Math.pow(node.weight, 2));
					answer = answer.add(val);
					union(node.to, node.from);
				}
			}
			
			System.out.println("#" + t + " " + answer.setScale(0, BigDecimal.ROUND_HALF_UP));
			
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
