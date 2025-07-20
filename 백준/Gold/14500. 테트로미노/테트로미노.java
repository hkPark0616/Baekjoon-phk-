import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
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
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;

                checkT(i, j);
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if(depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for(int[] delta: deltas) {
            int nx = x + delta[0];
            int ny = y + delta[1];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny])  {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    static void checkT(int x, int y) {
        // ㅗ
        if(x - 1 >= 0 && y - 1 >= 0 && y + 1 < M) {
            answer = Math.max(answer, map[x][y] + map[x - 1][y] + map[x][y - 1] + map[x][y + 1]);
        }
        // ㅏ
        if(x - 1 >= 0 && x + 1 < N && y + 1 < M) {
            answer = Math.max(answer, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y + 1]);
        }
        // ㅜ
        if(x + 1 < N && y - 1 >= 0 && y + 1 < M) {
            answer = Math.max(answer, map[x][y] + map[x + 1][y] + map[x][y - 1] + map[x][y + 1]);
        }
        // ㅓ
        if(x - 1 >= 0 && x + 1 < N && y - 1 >= 0) {
            answer = Math.max(answer, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y - 1]);
        }
    }
}