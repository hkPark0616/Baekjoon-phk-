import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static Point start, end;
    static char[][] map;
    static int[][][] dist;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
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

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        dist = new int[N][N][4];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                
                if(map[i][j] == '#') {
                    if(start == null) start = new Point(i, j);
                    else end = new Point(i, j);
                }
            }
        }

        int answer = Dijkstra();

        System.out.println(answer);
    }

    static int Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int d = 0; d < 4; d++) {
            pq.offer(new Node(start.x, start.y, d, 0));
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x;
            int y = cur.y;

            if(x == end.x && y == end.y) {
                return cur.cnt;
            }

            int nx = x + deltas[cur.dir][0];
            int ny = y + deltas[cur.dir][1];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == '*') continue;

            // 직진
            if(dist[nx][ny][cur.dir] > cur.cnt) {
                dist[nx][ny][cur.dir] = cur.cnt;
                pq.offer(new Node(nx, ny, cur.dir, cur.cnt));
            }

            // 거울 만나면 방향 바꾸기
            if(map[nx][ny] == '!') {
                for(int newDir: changeDir(cur.dir)) {
                    if(dist[nx][ny][newDir] > cur.cnt + 1) {
                        dist[nx][ny][newDir] = cur.cnt + 1;
                        pq.offer(new Node(nx, ny, newDir, cur.cnt + 1));
                    }
                }
            }
        }
        
        return -1;
    }

    static int[] changeDir(int dir) {
        if(dir == 0 || dir == 1) { // 상 || 하 -> 좌우
            return new int[] {2, 3};
        } else {
            return new int[] {0, 1};
        }
    }
}