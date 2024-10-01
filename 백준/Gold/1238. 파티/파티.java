import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, X, dist[], reverseDist[];
    static List<List<Node>> graph = new ArrayList<>();;
    static List<List<Node>> reverseGraph = new ArrayList<>();;

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        dist = new int[N + 1];
        reverseDist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(reverseDist, Integer.MAX_VALUE);

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, weight));
            reverseGraph.get(to).add(new Node(from, weight));
        }

        dist = Dijkstra(X, graph);
        reverseDist = Dijkstra(X, reverseGraph);

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            if (i != X && dist[i] != Integer.MAX_VALUE && reverseDist[i] != Integer.MAX_VALUE) {
                maxTime = Math.max(maxTime, dist[i] + reverseDist[i]);
            }
        }

        System.out.println(maxTime);
    }

    static int[] Dijkstra(int start, List<List<Node>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.vertex;

            if(visited[cur]) continue;
            visited[cur] = true;

            List<Node> nexts = graph.get(cur);

            for(Node next : nexts) {
                if(dist[next.vertex] > dist[cur] + next.weight) {
                    dist[next.vertex] = dist[cur] + next.weight;
                    pq.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        return dist;
    }
}