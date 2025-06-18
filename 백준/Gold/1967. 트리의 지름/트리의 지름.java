import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<List<Node>> graph = new ArrayList<>();
    static class Node implements Comparable<Node> {
        int to, weight;
        
        public Node(int to, int weight) {
            this.to = to;
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

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향
            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }

        // 트리의 끝 점 찾기
        int[] firstDist = Dijkstra(1);
        int far = 1; // 1번 노드부터 시
        for (int i = 2; i <= N; i++) {
            if (firstDist[i] > firstDist[far]) {
                far = i;
            }
        }

        // 트리의 끝 점(far)으로부터 가장 먼 거리 찾기
        int[] secondDist = Dijkstra(far);
        int diameter = 0;
        for(int i = 1; i <= N; i++) {
            diameter = Math.max(diameter, secondDist[i]);
        }

        System.out.println(diameter);
    }

    static int[] Dijkstra(int start) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.to;

            if(visited[now]) continue;
            visited[now] = true;

            for(Node nextNode: graph.get(now)) {
                if(!visited[nextNode.to] && dist[nextNode.to] > dist[now] + nextNode.weight) {
                    dist[nextNode.to] = dist[now] + nextNode.weight;
                    pq.offer(new Node(nextNode.to, dist[nextNode.to]));
                }
            }
        }

        return dist;
    }
}