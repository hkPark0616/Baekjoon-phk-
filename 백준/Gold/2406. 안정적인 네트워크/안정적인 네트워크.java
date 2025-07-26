import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] parent;
    static int[][] cost;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static List<int[]> addedEdges = new ArrayList<>();
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cost = new int[N + 1][N + 1];
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        List<int[]> init = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 본사 컴퓨터 제외
        for (int i = 2; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                pq.offer(new Edge(i, j, cost[i][j]));
            }
        }

        int X = 0;
        int K = 0;
        
        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if(!isSame(e.from, e.to)) {
                union(e.from, e.to);
                X += e.weight;
                addedEdges.add(new int[] {e.from, e.to});
                K++;
            }
        }

        System.out.println(X + " " + K);
        for(int[] edge: addedEdges) {
            System.out.println(edge[1] + " " + edge[0]);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) parent[b] = a;
    }

    static int find(int a) {
        if(a == parent[a]) return parent[a];

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}