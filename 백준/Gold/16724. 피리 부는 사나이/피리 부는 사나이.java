import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, cnt;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // U D L R
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M]; // 0: 방문 X / 1: 방문중 / 2: 완료

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 모든 칸을 시작점으로 해서 방향 따라 갔을 때
        // 사이클 생기면 cnt++
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j] == 0) dfs(i, j);
            }
        }

        System.out.println(cnt);
    }

    static void dfs(int x, int y) {
        visited[x][y] = 1;

        int dir = getDir(map[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(visited[nx][ny] == 0) {
            dfs(nx, ny);
        } else if(visited[nx][ny] == 1) {
            // 방문 중인 곳 다시 돌아오면 사이클
            cnt++;
        }

        // 완료
        visited[x][y] = 2;
    }

    static int getDir(char c) {
        int dir = 0;
        switch (c) {
            case 'U':
                dir = 0;
                break;
            case 'D':
                dir = 1;
                break;
            case 'L':
                dir = 2;
                break;
            case 'R':
                dir = 3;
                break;
        }

        return dir;
    }
}