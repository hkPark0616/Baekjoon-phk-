import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map, cost;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Node implements Comparable<Node> {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        cost = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(cost[i], Integer.MAX_VALUE);
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cost[0][0] = 0;

        Dijkstra();

        System.out.println(cost[N-1][N-1]);

    }

    static void Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            int x = n.x, y = n.y;

            if (x == N-1 && y == N-1) return;
            
            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                int diff = Math.abs(map[x][y] - map[nx][ny]);
                int next = Math.max(cost[x][y], diff);

                if(cost[nx][ny] > next) {
                    cost[nx][ny] = next;
                    pq.offer(new Node(nx ,ny, cost[nx][ny]));
                }
            }
        }
    }
}