import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] before, after;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        before = new int[N][M];
        after = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean done = false;
        for(int i = 0; i < N && !done; i++) {
            for(int j = 0; j < M && !done; j++) {
                if(before[i][j] != after[i][j]) {
                    bfs(i, j, before[i][j], after[i][j]);
                    done = true;
                }
            }
        }

        boolean isSame = true;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(before[i][j] != after[i][j]) {
                    isSame = false;
                    break;
                }
            }
        }

        System.out.println(isSame ? "YES" : "NO");
    }

    static void bfs(int a, int b, int oldV, int newV) {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        before[a][b] = newV;
        visited[a][b] = true;
        q.offer(new int[] {a, b});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(!visited[nx][ny] && before[nx][ny] == oldV) {
                    before[nx][ny] = newV;
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
    }
}