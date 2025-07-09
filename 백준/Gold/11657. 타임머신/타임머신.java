import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static long[] dist;
    static List<Edge> graph = new ArrayList<>();
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.add(new Edge(A, B, C));
        }

        boolean result = bellmanford(1);

        if(result == false) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if(dist[i] == Long.MAX_VALUE) System.out.println(-1);
                else System.out.println(dist[i]);
        	}
        }
    }

    static boolean bellmanford(int start) {
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Edge e = graph.get(j);

                if(dist[e.from] != Long.MAX_VALUE && dist[e.to] > dist[e.from] + e.cost) {
                    dist[e.to] = dist[e.from] + e.cost;
                }
            }
        }

        for(int i = 0; i < M; i++) {
            Edge e = graph.get(i);
            if(dist[e.from] != Long.MAX_VALUE && dist[e.to] > dist[e.from] + e.cost) {
                return false;
            }
        }
        
        return true;
    }
}