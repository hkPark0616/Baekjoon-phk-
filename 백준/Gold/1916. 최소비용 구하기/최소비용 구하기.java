import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, dist[];
    static int s, d;
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
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        Dijkstra(s);

    }

    static void Dijkstra(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        dist[s] = 0;

        pq.offer(new Node(s, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int cur = node.vertex;

            if(visited[cur]) continue;

            visited[cur] = true;

            List<Node> next = graph.get(cur);
            for(int i = 0; i < next.size(); i++){
                if(dist[next.get(i).vertex] > dist[cur] + next.get(i).weight){
                    dist[next.get(i).vertex] = dist[cur] + next.get(i).weight;
                    pq.offer(new Node(next.get(i).vertex, dist[next.get(i).vertex]));
                }
            }
        }

        System.out.println(dist[d]);
    }
}
