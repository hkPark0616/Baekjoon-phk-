import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int M, N;
    static int[][] map, dist;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Node implements Comparable<Node> {
        int x, y, cost;
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dist = new int[M][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(Dijkstra());
    }

    static int Dijkstra() {
        if (map[0][0] == -1 || map[M-1][N-1] == -1) return -1;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = map[0][0];
        pq.offer(new Node(0, 0, map[0][0]));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == M-1 && cur.y == N-1) return cur.cost;
            if (cur.cost > dist[cur.x][cur.y]) continue;

            for(int[] delta: deltas) {
                int nx = cur.x + delta[0];
                int ny = cur.y + delta[1];
                
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (map[nx][ny] == -1) continue;

                int nextCost = cur.cost + map[nx][ny];
                if (nextCost < dist[nx][ny]) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new Node(nx, ny, nextCost));
                }
            }
        }
        return -1;
    }
}