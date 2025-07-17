import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, K;
    static List<List<Edge>> graph;
    static int[] dist;
    static boolean[] visited;
    static boolean[] friends;
    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            visited = new boolean[N + 1];
            friends = new boolean[N + 1];
            graph = new ArrayList<>();

            for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            for(int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph.get(b).add(new Edge(a, c));
                graph.get(a).add(new Edge(b, c));
            }

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int k = 0; k < K; k++) {
                int roomNum = Integer.parseInt(st.nextToken());
                friends[roomNum] = true;
            }

            // 모든 친구마다 각 방의 최단 거리
            int[][] allDist = new int[K][N + 1];
            int idx = 0;
            for(int i = 1; i <= N; i++) {
                if(friends[i]) {
                    allDist[idx++] = Dijkstra(i);
                }
            }

            int min = Integer.MAX_VALUE;
            int room = 0;
            for(int i = 1; i <= N; i++) {
                int sum = 0;
                for(int j= 0; j < K; j++) {
                    sum += allDist[j][i];
                }

                if(sum < min) {
                    min = sum;
                    room = i;
                }
            }

            System.out.println(room);
        }
    }

    static int[] Dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if(dist[cur.to] < cur.weight) continue;

            for(Edge next: graph.get(cur.to)) {
                if(dist[next.to] > cur.weight + next.weight) {
                    dist[next.to] = cur.weight + next.weight;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }
}