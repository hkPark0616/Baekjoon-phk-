import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int H, W, sr, sc, fr, fc;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        fr = Integer.parseInt(st.nextToken()) - 1;
        fc = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[sr][sc] = true;
        q.offer(new int[] {sr, sc, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            if(x == fr && y == fc) return cnt;

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(!visited[nx][ny] && canMove(nx, ny)) {
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny, cnt + 1});
                }
            }
        }
        
        return -1;
    }

    static boolean canMove(int r, int c) {
        if (r + H - 1 >= N || c + W - 1 >= M) return false;
        
        for (int i = r; i < r + H; i++) {
            for (int j = c; j < c + W; j++) {
                if (map[i][j] == 1) return false;
            }
        }

        return true;
    }
}