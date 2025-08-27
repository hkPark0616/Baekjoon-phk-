import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int M, N;
    static int[][] map;
    static boolean[][][] visited;
    static Robot start, end;
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static class Robot {
        int x, y, dir, cnt;

        public Robot(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N][4];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine()); 
        int x = Integer.parseInt(st.nextToken()) - 1; 
        int y = Integer.parseInt(st.nextToken()) - 1; 
        int dir = Integer.parseInt(st.nextToken()) - 1; 
        start = new Robot(x, y, changeDir(dir), 0); 
        
        st = new StringTokenizer(br.readLine()); 
        x = Integer.parseInt(st.nextToken()) - 1; 
        y = Integer.parseInt(st.nextToken()) - 1; 
        dir = Integer.parseInt(st.nextToken()) - 1; 
        end = new Robot(x, y, changeDir(dir), 0);

        bfs();
    }

    static void bfs() {
        ArrayDeque<Robot> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y][start.dir] = true;

        while(!q.isEmpty()) {
            Robot r = q.poll();
            int x = r.x;
            int y = r.y;
            int dir = r.dir;
            int cnt = r.cnt;

            if(x == end.x && y == end.y && dir == end.dir) {
                System.out.println(cnt);
                return;
            }

            // 회전
            int leftDir = (dir + 3) % 4;
            int rightDir = (dir + 1) % 4;

            if(!visited[x][y][leftDir]) {
                visited[x][y][leftDir] = true;
                q.offer(new Robot(x, y, leftDir, cnt + 1));
            }

            if(!visited[x][y][rightDir]) {
                visited[x][y][rightDir] = true;
                q.offer(new Robot(x, y, rightDir, cnt + 1));
            }

            // 전진
            for(int i = 1; i <= 3; i++) {
                int nx = x + deltas[dir][0] * i;
                int ny = y + deltas[dir][1] * i;

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) break;
                if(map[nx][ny] == 1) break;

                if(!visited[nx][ny][dir]) {
                    visited[nx][ny][dir] = true;
                    q.offer(new Robot(nx, ny, dir, cnt + 1));
                }
            }
        }
    }
    
    // 동, 서, 남, 북 입력을 동, 남, 서, 북(deltas)에 맞게
    static int changeDir(int dir) {
        if(dir == 0) return 0;
        if(dir == 1) return 2;
        if(dir == 2) return 1;
        return 3;
    }
}