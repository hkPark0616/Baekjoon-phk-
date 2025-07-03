import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int V, E, P;
    static List<List<Edge>> graph = new ArrayList<>();
    static boolean[] visited;
    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
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

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= V; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        visited = new boolean[V + 1];
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Dijkstra(1, dist);

        visited = new boolean[V + 1];
        int[] distP = new int[V + 1];
        Arrays.fill(distP, Integer.MAX_VALUE);
        Dijkstra(P, distP);

        if(dist[V] == dist[P] + distP[V]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    static void Dijkstra(int start, int[] dist) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;

            if(visited[cur]) continue;
            visited[cur] = true;

            List<Edge> edgeList = graph.get(cur);
            for(Edge nextEdge: edgeList) {
                int next = dist[cur] + nextEdge.weight;

                if(dist[nextEdge.to] > next) {
                    dist[nextEdge.to] = next;
                    pq.offer(new Edge(nextEdge.to, dist[nextEdge.to]));
                }
            }
        }
    }
}