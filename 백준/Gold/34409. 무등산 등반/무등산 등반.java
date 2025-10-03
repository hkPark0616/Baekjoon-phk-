import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, sx, sy;
    static int[][] map;
    static long[][] cost;
    static int a, b ,c;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Node implements Comparable<Node> {
        int x, y;
        long w;

        public Node(int x, int y, long w) {
            this.x = x; 
            this.y = y; 
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.w, o.w);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        map = new int[N + 1][M + 1];
        cost = new long[N + 1][M + 1];
        for(int i = 0; i <= N; i++) Arrays.fill(cost[i], Long.MAX_VALUE);

        int tx = 1, ty = 1; // 정상 좌표
        int maxH = -1;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > maxH) { maxH = map[i][j]; tx = i; ty = j; }
            }
        }

        cost[sx][sy] = 0;
        Dijkstra(tx, ty);

        System.out.println(cost[tx][ty] == Long.MAX_VALUE ? -1 : cost[tx][ty]);
    }

    static void Dijkstra(int tx, int ty) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sx, sy, 0));

        while(!pq.isEmpty()) {
            Node n = pq.poll();
            int x = n.x, y = n.y;

            if(cost[x][y] < n.w) continue;
            if(x == tx && y == ty) return;

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 1 || ny < 1 || nx > N || ny > M) continue;

                int cur = map[x][y];
                int next = map[nx][ny];
                long nextCost = 0;
                if(cur == next) nextCost = 1;
                else if(cur < next){
                    if((next - cur) > c) continue;
                    nextCost = (next - cur) * a;  
                } else if(cur > next) {
                    if((cur - next) > c) continue;
                    nextCost = (cur - next) * b;
                }

                if(cost[nx][ny] > cost[x][y] + nextCost) {
                    cost[nx][ny] = cost[x][y] + nextCost;
                    pq.offer(new Node(nx, ny, cost[nx][ny]));
                }
            }
        }
    }
}