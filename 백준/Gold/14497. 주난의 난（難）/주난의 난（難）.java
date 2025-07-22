import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, x1, y1, x2, y2;
    static char[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                char c = str.charAt(j);
                map[i][j] = c;
            }
        }

        System.out.println(bfs());
        
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x1, y1, 0});
        visited[x1][y1] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int c= cur[2];

            if(x == x2 && y == y2) {
                return c;
            }

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if(map[nx][ny] == '1' || map[nx][ny] == '#' || map[nx][ny] == '*') {
                        q.offerLast(new int[] {nx, ny, c + 1});
                    } else if(map[nx][ny] == '0') {
                        q.offerFirst(new int[] {nx, ny, c});
                    }
                }
            }
        }

        return -1;
    }
}