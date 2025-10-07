import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, A, B, C;
    static int[] friends = new int[3];
    static int[][] dist;
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
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        dist = new int[3][N + 1];
        for(int i = 0; i < 3; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++) friends[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            
            graph.get(D).add(new Edge(E, L));
            graph.get(E).add(new Edge(D, L));
        }

        for(int i = 0; i < 3; i++) Dijkstra(i, friends[i]); // 친구별로 수행

        // 세 친구가 모두 도착 가능한 지점 중 가장 늦게 도착하는 사람의 시간이 최소인 곳
        int maxDist = -1;
        int result = -1;
        for (int i = 1; i <= N; i++) {
            if (dist[0][i] == Integer.MAX_VALUE || dist[1][i] == Integer.MAX_VALUE || dist[2][i] == Integer.MAX_VALUE) continue;
        
            int min = Math.min(dist[0][i], Math.min(dist[1][i], dist[2][i])); // 가장 가까운 친구 거리
            if (min > maxDist) {
                maxDist = min;
                result = i;
            }
        }
        
        System.out.println(result);

    }

    static void Dijkstra(int idx, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[idx][start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;

            if(e.weight > dist[idx][cur]) continue;

            for(Edge n: graph.get(cur)) {
                int next = e.weight + n.weight;

                if(dist[idx][n.to] > next) {
                    dist[idx][n.to] = next;
                    pq.offer(new Edge(n.to, next));
                }
            }
        }
    }
}