import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, J, K;
    static int[] dist, A, B;
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
        
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        J = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        A = new int[K];
        for(int i = 0; i < K; i++) A[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        B = new int[K];
        for(int i = 0; i < K; i++) B[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int Z = Integer.parseInt(st.nextToken());

            graph.get(X).add(new Edge(Y, Z));
            graph.get(Y).add(new Edge(X, Z));
        }

        Dijkstra();

        int minA = Integer.MAX_VALUE;
        for(int a: A) minA = Math.min(minA, dist[a]);
        
        int minB = Integer.MAX_VALUE;
        for(int b: B) minB = Math.min(minB, dist[b]);

        if(minA == Integer.MAX_VALUE && minB == Integer.MAX_VALUE) { // 둘 다 X
            System.out.println(-1);
        } else if(minA != Integer.MAX_VALUE && minB == Integer.MAX_VALUE) { // A만
            System.out.println('A');
            System.out.println(minA);
        } else if(minA == Integer.MAX_VALUE && minB != Integer.MAX_VALUE) { // B만
            System.out.println('B');
            System.out.println(minB);
        } else { // 둘 다 O -> 최소
            if(minA <= minB) {
                System.out.println('A');
                System.out.println(minA);
            } else {
                System.out.println('B');
                System.out.println(minB);
            }
        }
    }

    static void Dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[J] = 0;
        pq.offer(new Edge(J, 0));

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;
            int d = e.weight;

            if(d > dist[cur]) continue;

            for(Edge next: graph.get(cur)) {
                int nextDist = d + next.weight;
                if(dist[next.to] > nextDist) {
                    dist[next.to] = nextDist;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }
}