import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, T;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();
        
        System.out.println((result <= T && result != -1) ? result : "Fail");
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[0][0][0] = true;
        q.offer(new int[] {0, 0, 0, 0});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];
            int gram = cur[3];
            
            if(time > T) continue;
            if(x == N - 1 && y == M - 1) {
                return time;
            }

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(gram == 1) {
                    if(!visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        q.offer(new int[] {nx, ny, time + 1, 1});
                    }
                } else {
                    if(map[nx][ny] != 1 && !visited[nx][ny][0]) {
                        int next = map[nx][ny] == 2 ? 1 : 0;
                        visited[nx][ny][0] = true;
                        q.offer(new int[] {nx, ny, time + 1, next});
                    }
                }
            }
            
        }
        
        return -1;
    }
}