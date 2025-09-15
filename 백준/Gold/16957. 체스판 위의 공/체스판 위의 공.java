import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int R, C;
    static int[][] map, count, dpRow, dpCol;
    static int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        count = new int[R][C];
        // x, y에서 출발한 공이 최종적으로 도착하는 좌표 각각 저장
        dpRow = new int[R][C];
        dpCol = new int[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dpRow[i][j] = -1;
                dpCol[i][j] = -1;
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int[] pos = dfs(i, j);
                count[pos[0]][pos[1]]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(count[i][j]);
                if(j != C - 1) sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[] dfs(int x, int y) {
        if(dpRow[x][y] != -1) return new int[] {dpRow[x][y], dpCol[x][y]};

        int min = map[x][y];
        int r = x, c = y;

        for(int[] delta: deltas) {
            int nr = x + delta[0];
            int nc = y + delta[1];

            if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if(map[nr][nc] < min) {
                min = map[nr][nc];
                r = nr;
                c = nc;
            }
        }

        if(r == x && c == y) {
            dpRow[x][y] = r;
            dpCol[x][y] = c;
        } else {
            int[] pos = dfs(r, c);
            dpRow[x][y] = pos[0];
            dpCol[x][y] = pos[1];
        }

        return new int[]{dpRow[x][y], dpCol[x][y]};
    }
}
