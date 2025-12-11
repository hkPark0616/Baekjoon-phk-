import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, V, E, A, B;
    static int[] teammates, distA, distB;
    static List<List<Edge>> graph = new ArrayList<>();
    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        teammates = new int[N];
        for(int i = 0; i < N; i++) teammates[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= V; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, l));
            graph.get(b).add(new Edge(a, l));
        }

        distA = Dijkstra(A);
        distB = Dijkstra(B);

        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        int sum = 0;
        for(int t: teammates) {
            if(distA[t] == Integer.MAX_VALUE || distB[t] == Integer.MAX_VALUE) sum -= -1;
            else sum += (distA[t] + distB[t]);
        }

        System.out.println(sum);
    }

    static int[] Dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;
            int w = e.weight;

            if(w > dist[cur]) continue;

            for(Edge next: graph.get(cur)) {
                if(dist[next.to] > w + next.weight) {
                    dist[next.to] = w + next.weight;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }
}