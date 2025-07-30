import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int W, H;
    static Point start, end;
    static char[][] map;
    static int[][][] dist;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static class Node implements Comparable<Node> {
        int x, y, dir, cnt;

        public Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }
    static class Point {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        dist = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
        
                if (map[i][j] == 'C') {
                    if (start == null) start = new Point(i, j);
                    else end = new Point(i, j);
                }
            }
        }

        int answer = Dijkstra(start.x, start.y);

        System.out.println(answer);
    }

    static int Dijkstra(int a, int b) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int d = 0; d < 4; d++) {
            pq.offer(new Node(a, b, d, 0));
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int cnt = cur.cnt;

            if(x == end.x && y == end.y) {
                return cnt;
            }

            for(int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '*') continue;

                int next = cnt + (cur.dir == d ? 0 : 1); // 방향이 다르면 거울 설치

                if(dist[nx][ny][d] > next) {
                    dist[nx][ny][d] = next;
                    pq.offer(new Node(nx, ny, d, next));
                }
            }
        }
        return -1;
    }
}