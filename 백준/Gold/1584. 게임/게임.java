import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map = new int[501][501];
    static int[][] dist = new int[501][501];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        // 위험(1)
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            fillArea(x1, y1, x2, y2, 1);
        }

        M = Integer.parseInt(br.readLine());

        // 죽음(2)
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            fillArea(x1, y1, x2, y2, 2);
        }

        for(int i = 0; i <= 500; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        bfs();

        System.out.println(dist[500][500] == Integer.MAX_VALUE ? -1 : dist[500][500]);
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        dist[0][0] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];
                
                if(nx >= 0 && nx < 501 && ny >= 0 && ny < 501 && map[nx][ny] != 2) {
                    int cost = map[nx][ny] == 1 ? 1 : 0;
                    
                    if(dist[nx][ny] > dist[x][y] + cost) {
                        dist[nx][ny] = dist[x][y] + cost;
                        if(cost == 1) q.offerLast(new int[] {nx, ny});
                        else q.offerFirst(new int[] {nx, ny});
                    }
                }
            }
        }
    }

    static void fillArea(int x1, int y1, int x2, int y2, int t) {
        int sx = Math.min(x1, x2);
        int sy = Math.min(y1, y2);
        int ex = Math.max(x1, x2);
        int ey = Math.max(y1, y2);
    
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                map[i][j] = t;
            }
        }
    }
}