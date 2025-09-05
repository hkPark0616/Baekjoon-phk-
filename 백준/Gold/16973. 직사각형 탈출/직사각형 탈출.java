import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int H, W, sr, sc, fr, fc;
    static int[][] map, sum;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        sum = new int[N + 1][M + 1];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                sum[i][j] = map[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
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
        int r2 = r + H - 1;
        int c2 = c + W - 1;
        
        if (r2 >= N || c2 >= M) return false;

        int result = sum[r2 + 1][c2 + 1] - sum[r2 + 1][c] - sum[r][c2 + 1] + sum[r][c];

        return result == 0;
    }
}