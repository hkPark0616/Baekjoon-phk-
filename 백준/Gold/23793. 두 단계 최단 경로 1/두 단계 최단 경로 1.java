import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
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
        M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        int[] distX = dijkstra(x, -1);
        int[] distY = dijkstra(y, -1);
        int[] distNoY = dijkstra(x, y);

        int answer1 = (distX[y] == Integer.MAX_VALUE || distY[z] == Integer.MAX_VALUE) ? -1 : distX[y] + distY[z];
        int answer2 = distNoY[z] == Integer.MAX_VALUE ? -1 : distNoY[z];
        
        System.out.println(answer1 + " " + answer2);        
    }

    static int[] dijkstra(int start, int skip) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;
            int w = e.weight;

            if (w > dist[cur]) continue;

            for(Edge edgeList: graph.get(cur)) {
                if (edgeList.to == skip) continue;
                
                int next = dist[cur] + edgeList.weight;
                
                if(dist[edgeList.to] > next) {
                    dist[edgeList.to] = next;
                    pq.offer(new Edge(edgeList.to, dist[edgeList.to]));
                 }
            }
        }

        return dist;
    }
}