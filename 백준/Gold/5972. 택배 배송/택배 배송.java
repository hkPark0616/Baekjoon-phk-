import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] dist;
    static boolean[] visited;
    static List<List<Edge>> graph = new ArrayList<>();

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        Dijkstra();

        System.out.println(dist[N]);
    }

    static void Dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;

            if(visited[cur]) continue;
            visited[cur] = true;

            List<Edge> next = graph.get(cur);
            for(Edge nextEdge: next){
                int nextCost = dist[cur] + nextEdge.cost;
                if(dist[nextEdge.to] > nextCost) {
                    dist[nextEdge.to] = nextCost;
                    pq.offer(new Edge(nextEdge.to, dist[nextEdge.to]));
                }
            }
        }
        
    }
}