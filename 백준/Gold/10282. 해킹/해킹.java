import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, d, c;
    static List<List<Node>> graph;
    static int[] dist;
    static boolean[] visited;
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
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            visited = new boolean[n + 1];
            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            graph = new ArrayList<>();
            for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());

            for(int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new Node(a, s));
            }
            
            Dijkstra();

            int count = 0;
            int maxTime = 0;
            for(int time: dist) {
                if(time != Integer.MAX_VALUE) {
                    count++;
                    maxTime = Math.max(maxTime, time);
                }
            }

            System.out.println(count + " " + maxTime);
        }
    }

    static void Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(c, 0));
        dist[c] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();

            int current = node.to;

            if(visited[current]) continue;
            visited[current] = true;

            List<Node> next = graph.get(current);

            for(Node nextNode: next) {
                int nextCost = dist[current] + nextNode.weight;
                if(dist[nextNode.to] > nextCost) {
                    dist[nextNode.to] = nextCost;
                    pq.offer(new Node(nextNode.to, dist[nextNode.to]));
                }
            }
        }
    }
}