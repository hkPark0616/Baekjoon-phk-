import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    
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

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) {
                    if(bfs(i, j)) cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    static boolean bfs(int a, int b) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[a][b] = true;
        q.offer(new int[] {a, b});
        boolean isPeak = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] > map[x][y]) isPeak = false; // 봉우리 아님
                if(!visited[nx][ny] && map[x][y] == map[nx][ny]) {
                     visited[nx][ny] = true;
                     q.offer(new int[] {nx, ny});
                }
            }
        }
        
        return isPeak;
    }
}