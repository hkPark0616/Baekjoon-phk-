import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, A, B;
    static long[] distA, distB;
    static List<List<Edge>> graph = new ArrayList<>();
    static class Edge implements Comparable<Edge> {
        int to;
        long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        distA = new long[N + 1];
        distB = new long[N + 1];
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            distA[i] = Long.MAX_VALUE;
            distB[i] = Long.MAX_VALUE;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
        
        Dijkstra(A, distA);
        Dijkstra(B, distB);

        long minTime = distA[B];
        List<Integer> path = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(distA[i] != Long.MAX_VALUE && distB[i] != Long.MAX_VALUE && minTime == distA[i] + distB[i]) {
                path.add(i);
            }
        }

        Collections.sort(path);

        sb.append(path.size()).append("\n");
        for(int p: path) {
            sb.append(p).append(" ");
        }

        System.out.println(sb);
    }

    static void Dijkstra(int start, long[] dist) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;
            long w = e.weight;

            if(dist[cur] < w) continue;
            
            if(visited[cur]) continue;
            visited[cur] = true;

            for(Edge nextEdge: graph.get(cur)) {
                long next = dist[cur] + nextEdge.weight;

                if(dist[nextEdge.to] > next) {
                    dist[nextEdge.to] = next;
                    pq.offer(new Edge(nextEdge.to, dist[nextEdge.to]));
                }
            }
        }
    }
}
