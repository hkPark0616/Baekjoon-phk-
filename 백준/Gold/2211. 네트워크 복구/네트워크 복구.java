import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, cnt;
    static int[] dist;
    static List<List<Node>> graph = new ArrayList<>();
    static class Node implements Comparable<Node> {
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        int[] results = Dijkstra();
        sb.append(N - 1).append("\n");
        for(int i = 2; i <= N; i++) {
            sb.append(i).append(" ").append(results[i]).append("\n");
        }

        System.out.println(sb);
    }

    static int[] Dijkstra() {
        int[] result = new int[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            int cur = n.to;
            int time = n.weight;

            if(dist[cur] < time) continue;

            for(Node next: graph.get(cur)) {
                int nextCost = dist[cur] + next.weight;

                if(dist[next.to] > nextCost) {
                    dist[next.to] = nextCost;
                    pq.offer(new Node(next.to, dist[next.to]));
                    result[next.to] = cur;
                    cnt++;
                }
            }
        }

        return result;
    }
}