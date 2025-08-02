import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, W;
    static double limit;
    static Location[] locations;
    static double[] dist;
    static List<List<Node>> graph = new ArrayList<>();
    static class Location {
        int x, y;
        
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        double weight;

        public Node(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        limit = Double.parseDouble(br.readLine());

        locations = new Location[N + 1];
        dist = new double[N + 1];
        Arrays.fill(dist, Double.MAX_VALUE);

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            locations[i] = new Location(x, y);
        }

        for(int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(new Node(y, 0));
            graph.get(y).add(new Node(x, 0));
        }


        for(int i = 1; i <= N; i++) {
            Location A = locations[i];
            for(int j = i + 1; j <= N; j++) {
                Location B = locations[j];

                double dx = A.x - B.x;
                double dy = A.y - B.y;
                double d = Math.sqrt(dx * dx + dy * dy);

                if(d <= limit) {
                    graph.get(i).add(new Node(j, d));
                    graph.get(j).add(new Node(i, d));
                }
            }
        }

        Dijkstra();
        
        if (dist[N] == Double.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println((long)Math.floor(dist[N] * 1000));
        }
    }

    static void Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.to;
            double len = node.weight;

            if(dist[cur] < len) continue;

            for(Node next: graph.get(cur)) {
                double nextCost = dist[cur] + next.weight;

                if(dist[next.to] > nextCost) {
                    dist[next.to] = nextCost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}