import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static int N, M, S, D;
    static int INF = Integer.MAX_VALUE;
    static int[] dist, prev;
    static List<List<Node>> graph = new ArrayList<>();

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        prev = new int[N + 1];
        Arrays.fill(prev, -1);

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, w));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        Dijkstra(S);

        // 경로 역추적
        List<Integer> path = new ArrayList<>();
        for(int at = D; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path); // 경로를 뒤집음

        // 최소 비용
        System.out.println(dist[D]);
        // 방문 도시 개수
        System.out.println(path.size());
        // 방문 도시
        for(int city : path) {
            System.out.print(city + " ");
        }
    }

    static void Dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        List<List<Integer>> paths = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            paths.add(new ArrayList<>());
        }

        dist[start] = 0;

        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.vertex]) continue;

            visited[cur.vertex] = true;

            List<Node> nexts = graph.get(cur.vertex);
            for(Node next : nexts){
                if(dist[next.vertex] > dist[cur.vertex] + next.weight){
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    pq.offer(new Node(next.vertex, dist[next.vertex]));
                    prev[next.vertex] = cur.vertex;
                }
            }
        }
    }
}