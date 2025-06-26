import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] parent;
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Node implements Comparable<Node> {
		int from, to;
        double cost;
		public Node(int from, int to, double cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return Double.compare(this.cost, o.cost);
		}
	}
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        parent = new int[N + 1];
        Point[] points = new Point[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        // 황선자를 포함하여 우주신들의 좌표 저장
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            points[i] = new Point(X, Y);
        }

        // 이미 연결된 통로 -> union
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            union(X, Y);
        }

        // 두 점 사이의 거리 구하기(중복 및 동일 점 계산 제외)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                double dist = getDist(points[i], points[j]);
                pq.offer(new Node(i, j, dist));
            }
        }

        // 크루스칼
        double min = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(!isSame(node.from, node.to)) {
                union(node.from, node.to);
                min += node.cost;
            }
        }

        System.out.printf("%.2f", min);
    }

    // 거리 계산
    static double getDist(Point p1, Point p2) {
        long dx = p1.x - p2.x;
        long dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if(a == parent[a]) return parent[a];

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}