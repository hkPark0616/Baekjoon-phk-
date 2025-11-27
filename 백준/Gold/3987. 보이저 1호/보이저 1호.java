import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, PR, PC;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static char[] DIR = {'U', 'R', 'D', 'L'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                char c = s.charAt(j);
                map[i][j] = c;
            }
        }

        st = new StringTokenizer(br.readLine());
        PR = Integer.parseInt(st.nextToken());
        PC = Integer.parseInt(st.nextToken());

        int bestDir = 0;
        int bestTime = -1;
        boolean infinite = false;
    
        for(int d = 0; d < 4; d++) {
            int time = bfs(d);
    
            if(time == -1) {
                if(!infinite) {  
                    bestDir = d;
                    infinite = true;
                }
            } else {
                if(!infinite && time > bestTime) {
                    bestTime = time;
                    bestDir = d;
                }
            }
        }
    
        System.out.println(DIR[bestDir]);
        if(infinite) System.out.println("Voyager");
        else System.out.println(bestTime);
    }

    static int bfs(int dir) {
        boolean[][][] visited = new boolean[N][M][4];
        int x = PR - 1, y = PC - 1, d = dir, cnt = 0;

        while(true) {
            int nx = x + deltas[d][0];
            int ny = y + deltas[d][1];

            cnt++;

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) return cnt;

            if(map[nx][ny] == 'C') return cnt;

            // 이미 방문한 곳 다시 방문 -> 무한루프
            if(visited[nx][ny][d]) return -1;
            visited[nx][ny][d] = true;

            // 0, 1, 2, 3 = 상, 우, 하, 좌
            if(map[nx][ny] == '\\') {
                if(d == 0) d = 3;
                else if(d == 3) d = 0;
                else if(d == 2) d = 1;
                else d = 2;
            } else if(map[nx][ny] == '/') {
                if(d == 0) d = 1;
                else if(d == 1) d = 0;
                else if(d == 2) d = 3;
                else d = 2;
            }
            
            x = nx;
            y = ny;
        }
    }
}