import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int M, N, H;
    static int[][][] boxes;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static int[][] deltas = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        boxes = new int[H][N][M];

        for(int h = 0; h < H; h++) {
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());    
                for(int j = 0; j < M; j++) {
                    boxes[h][i][j] = Integer.parseInt(st.nextToken());
                    if(boxes[h][i][j] == 1) q.offer(new int[] {h, i, j});
                }
            }
        }

        if(check()) {
            System.out.println(0);
        } else {
            bfs();
            System.out.println(getMaxDays());
        }
    }

    static void bfs() {
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int h = cur[0];
            int x = cur[1];
            int y = cur[2];

            for(int[] delta: deltas) {
                int nh = h + delta[0];
                int nx = x + delta[1];
                int ny = y + delta[2];

                if(nh >= 0 && nh < H && nx >= 0 && nx < N && ny >= 0 && ny < M && boxes[nh][nx][ny] == 0) {
                    boxes[nh][nx][ny] = boxes[h][x][y] + 1;
                    q.offer(new int[] {nh, nx, ny});
                }
            }
        }
    }

    static int getMaxDays() {
        int maxDays = 0;
        for(int h = 0; h < H; h++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(boxes[h][i][j] == 0) return -1;
                    maxDays = Math.max(maxDays, boxes[h][i][j]);
                }
            }
        }

        return maxDays - 1;
    }

    static boolean check() {
        for(int h = 0; h < H; h++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(boxes[h][i][j] == 0) return false; 
                }
            }
        }

        return true;
    }
}