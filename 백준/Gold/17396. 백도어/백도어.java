import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static long[] dist;
    static int[] visible;
    static List<List<Node>> graph = new ArrayList<>();
    
    static class Node implements Comparable<Node> {
        int to;
        long weight;

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        dist = new long[N];
        for(int i = 0; i < N; i++) {
            dist[i] = Long.MAX_VALUE;
            graph.add(new ArrayList<>());
        }

        visible = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) visible[i] = Integer.parseInt(st.nextToken());

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, t));
            graph.get(b).add(new Node(a, t));
        }
        

        System.out.println(Dijkstra());
    }

    static long Dijkstra() {
        if (visible[0] == 1) return -1;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.to;
            long time = node.weight;

            if(cur == N - 1) return time;

            if (dist[cur] < time) continue;

            List<Node> list = graph.get(cur);

            for(Node next: list) {
                if(next.to != N - 1 && visible[next.to] == 1) continue;
                
                long nextCost = dist[cur] + next.weight;

                if(dist[next.to] > nextCost) {
                    dist[next.to] = nextCost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        return -1;
    }
    
}