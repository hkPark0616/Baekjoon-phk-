import java.util.*;
import java.lang.*;
import java.io.*;

// 백준 24513
class Main {
    static int N, M;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static int[][] map, timeMap;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        timeMap = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1 || map[i][j] == 2) {
                    q.offer(new int[] {i, j, map[i][j], 0}); // x, y, 타입, 시간
                    timeMap[i][j] = 0;
                }
            }
        }

        bfs();

        int a = 0, b = 0, c = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) a++;
                else if(map[i][j] == 2) b++;
                else if(map[i][j] == 3) c++;
            }
        }

        System.out.println(a + " " + b + " " + c);
    }


    static void bfs() {
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int type = cur[2];
            int time = cur[3];

            if (map[x][y] == 3) continue;

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != -1) {
                    if(map[nx][ny] == 0) { // 아직 감염 안됨
                        map[nx][ny] = type;
                        timeMap[nx][ny] = time + 1;
                        q.offer(new int[] {nx, ny, type, time + 1});
                    } 
                    // 다른 바이러스가 동시에 만나면 3번 바이러스 생성
                    else if(timeMap[nx][ny] == time + 1 && map[nx][ny] != type && map[nx][ny] != 3) {
                        map[nx][ny] = 3;
                    } 
                }
            }
        }
    }
}