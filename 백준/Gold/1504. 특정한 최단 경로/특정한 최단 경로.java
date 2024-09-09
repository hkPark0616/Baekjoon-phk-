import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, dist[];
    static int v1, v2;
    static int INF = Integer.MAX_VALUE;
    static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        long dist1 = 0;
        dist1 += Dijkstra(1, v1);
        dist1 += Dijkstra(v1, v2);
        dist1 += Dijkstra(v2, V);

        long dist2 = 0;
        dist2 += Dijkstra(1, v2);
        dist2 += Dijkstra(v2, v1);
        dist2 += Dijkstra(v1, V);

        long answer = Math.min(dist1, dist2);
        if(answer >= INF) {
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }

    static int Dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int current = node.vertex;

            if(visited[node.vertex]) continue;

            visited[node.vertex] = true;

            List<Node> next = graph.get(current);
            for(int i = 0; i < next.size(); i++) {
                if(dist[next.get(i).vertex] > dist[current] + next.get(i).weight){
                    dist[next.get(i).vertex] = dist[current] + next.get(i).weight;
                    pq.offer(new Node(next.get(i).vertex, dist[next.get(i).vertex]));
                }
            }
        }

        return dist[end];
    }
}
