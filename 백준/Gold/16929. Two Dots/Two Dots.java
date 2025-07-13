import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static boolean hasCycle;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M];
        hasCycle = false;

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

         for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) {
                    dfs(i, j, -1, -1, 1);
                }
            }
        }

        System.out.println(hasCycle ? "Yes" : "No");
    }

    static void dfs(int x, int y, int px, int py, int len) {
        if (hasCycle) return;
        
        visited[x][y] = true;

        for(int[] delta: deltas) {
            int nx = x + delta[0];
            int ny = y + delta[1];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == board[x][y]) {
                if(!visited[nx][ny]) {
                    dfs(nx, ny, x, y, len + 1);
                } else if((nx != px || ny != py) && len >= 4) { // 방문했던 칸인데, 바로 직전에 왔던 칸이 아닌 경우 && 길이 충족
                    hasCycle = true;
                }
            }
        }
    }
}