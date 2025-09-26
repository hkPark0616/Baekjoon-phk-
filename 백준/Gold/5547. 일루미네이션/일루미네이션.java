import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int W, H;
    static int[][] map;
    static boolean[][] visited;
    // y가 짝수
    static int[][] deltas1 = {{-1, -1}, {0, -1}, {1, 0}, {0, 1}, {-1, 1}, {-1, 0}};
    // y가 홀수
    static int[][] deltas2 = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 0}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 2][W + 2];
        visited = new boolean[H + 2][W + 2];
        for(int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        visited[0][0] = true;
        q.offer(new int[] {0, 0});
        int answer = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            
            int[][] deltas = (y % 2 == 0) ? deltas1 : deltas2;

            for(int[] d: deltas) {
                int nx = x + d[0];
                int ny = y + d[1];

                if(nx < 0 || ny < 0 || nx >= W + 2 || ny >= H + 2) continue;

                if(map[ny][nx] == 1) {
                    answer++;
                } else if(!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }

        System.out.println(answer);
    }
}