import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, Q, size;
    static int sum, max;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = (int)Math.pow(2, N);
        map = new int[size][size];
        visited = new boolean[size][size];

        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int q = 0; q < Q; q++) {
            int L = Integer.parseInt(st.nextToken());
            rotateAndMelt(L);
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(!visited[i][j] && map[i][j] > 0) {
                     max = Math.max(max, bfs(i, j));   
                }
                sum += map[i][j];
            }
        }

        System.out.println(sum);
        System.out.println(max);
    }

    static void rotateAndMelt(int L) {
        // 2L × 2L 크기의 부분 격자로 나눈다. 그 후, 모든 부분 격자를 시계 방향으로 90도 회전
        int m = (int)Math.pow(2, L);
        int[][] temp = new int[size][size];
        for(int r = 0; r < size; r += m) {
            for(int c = 0; c < size; c += m) {
                
                for(int i = 0; i < m; i++) {
                    for(int j = 0; j < m; j++) {
                        temp[r + j][c + m - 1 - i] = map[r + i][c + j];
                    }
                }
            }
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                map[i][j] = temp[i][j];
            }
        }

        int[][] tempMelt = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(map[i][j] == 0) continue;

                int cnt = 0;
                for(int[] delta: deltas) {
                    int ni = i + delta[0];
                    int nj = j + delta[1];

                    if(ni >= 0 && ni < size && nj >= 0 && nj < size && map[ni][nj] > 0) {
                        cnt++;
                    }
                }

                if(cnt < 3) tempMelt[i][j] = 1;
            }
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                map[i][j] -= tempMelt[i][j];
            }
        }
    }

    static int bfs(int a, int b) {
        ArrayDeque<int[]> q=  new ArrayDeque<>();
        q.offer(new int[] {a, b});
        visited[a][b] = true;
        int cnt = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx >= 0 && nx < size && ny >= 0 && ny < size && map[nx][ny] > 0 && ! visited[nx][ny]) {
                    q.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}