import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, X, Y;
    static int[] dist;
    static List<List<Edge>> graph = new ArrayList<>();
    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());


        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i = 0; i < N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C));
        }

        Dijkstra();

        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
        }

        Arrays.sort(dist); // 가까운 집부터 방문
        
        int day = 1;
        int move = 0; // 하루 이동 거리
        for (int i = 0; i < N; i++) {
            int roundTrip = dist[i] * 2;
            if(roundTrip > X) {
                System.out.println(-1);   
                return;
            }

            if(roundTrip + move > X) { // 왕복할 수 없는 거리는 다음날
                day++;
                move = 0;
            }

            move += roundTrip;
        }
        
        System.out.println(day);
    }

    static void Dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[Y] = 0;
        pq.offer(new Edge(Y, 0));

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;
            int weight = e.weight;

            if(dist[cur] < weight) continue;

            for(Edge n: graph.get(cur)) {
                int next = dist[cur] + n.weight;

                if(dist[n.to] > next) {
                    dist[n.to] = next;
                    pq.offer(new Edge(n.to, dist[n.to]));
                }
            }
        }
    }
}