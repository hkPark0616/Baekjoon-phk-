import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, k;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[2][N];
        visited = new boolean[2][N];
        
        for(int i = 0; i < 2; i++) {
            String s = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) -  '0';
            }
        }

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[0][0] = true;
        q.offer(new int[] {0, 0, 0});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], time = cur[2];

            if(y >= N) return true;

            // i + 1
            int ny = y + 1;
            if(ny >= N) return true;
            if(map[x][ny] == 1 && ny > time && !visited[x][ny]) {
                visited[x][ny] = true;
                q.offer(new int[] {x, ny, time + 1});
            }

            // i - 1
            ny = y - 1;
            if(ny >= 0 && ny > time && map[x][ny] == 1 && !visited[x][ny]) {
                visited[x][ny] = true;
                q.offer(new int[] {x, ny, time + 1});
            }

            // 반대
            int nx = 1 - x;
            ny = y + k;
            if(ny >= N) return true;
            if(map[nx][ny] == 1 && ny > time && !visited[nx][ny]) {
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny, time + 1});
            }
        }
        
        return false;
    }
}