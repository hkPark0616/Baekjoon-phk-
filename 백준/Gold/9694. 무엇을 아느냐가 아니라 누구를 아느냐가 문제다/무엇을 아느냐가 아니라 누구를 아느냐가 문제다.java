import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] dist;
    static int[] meet;
    static List<List<Node>> graph;
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
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            dist = new int[M];
            graph = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                dist[i] = Integer.MAX_VALUE;
                graph.add(new ArrayList<>());
            }

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph.get(x).add(new Node(y, z));
                graph.get(y).add(new Node(x, z));
            }

            Dijkstra();
            
            sb.append("Case #").append(tc).append(": ");

            if(dist[M - 1] == Integer.MAX_VALUE) {
                sb.append(-1);
            } else {
                List<Integer> path = new ArrayList<>();
                for(int at = M - 1; at != 0; at = meet[at]) {
                    path.add(at);
                }
                Collections.reverse(path);

                sb.append(0).append(" ");
                for(int i = 0; i < path.size(); i++) {
                    sb.append(path.get(i));

                    if(i != path.size() - 1) sb.append(" ");
                }
            }
            
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void Dijkstra() {
        meet = new int[M];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.to;
            int cost = node.weight;

            for(Node nextNode: graph.get(cur)) {
                int nextCost = dist[cur] + nextNode.weight;

                if(dist[nextNode.to] > nextCost) {
                    dist[nextNode.to] = nextCost;
                    pq.offer(new Node(nextNode.to, dist[nextNode.to]));
                    meet[nextNode.to] = cur;
                }
            }
        }
    }
}