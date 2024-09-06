import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, W, H, map[][], min;
    static boolean[][][] visited;  // 3차원 배열로 x, y, 남은 이동 횟수 체크
    static int[] hX = {-1, -2, -2, -1, 1, 2, 2, 1}, hY = {-2, -1, 1, 2, 2, 1, -1, -2};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        map = new int[H][W];
        visited = new boolean[H][W][K + 1];  // K+1은 말의 최대 이동 횟수
        
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        min = Integer.MAX_VALUE;
        
        bfs(0, 0, K);
        
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
    
    static void bfs(int startX, int startY, int startK) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startX, startY, startK, 0});  // (x, y, 남은 이동 횟수, 이동 횟수)
        visited[startX][startY][startK] = true;
        
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            int k = temp[2];
            int moveCount = temp[3];
            
            if (x == H - 1 && y == W - 1) {
                min = Math.min(min, moveCount);
                continue;
            }
            
            // 일반 이동
            for (int d = 0; d < 4; d++) {
                int nx = x + (d == 0 ? -1 : d == 2 ? 1 : 0);
                int ny = y + (d == 1 ? 1 : d == 3 ? -1 : 0);
                if (nx >= 0 && nx < H && ny >= 0 && ny < W && !visited[nx][ny][k] && map[nx][ny] == 0) {
                    visited[nx][ny][k] = true;
                    queue.offer(new int[] {nx, ny, k, moveCount + 1});
                }
            }
            
            // 말 이동
            if (k > 0) {
                for (int d = 0; d < 8; d++) {
                    int nx = x + hX[d];
                    int ny = y + hY[d];
                    if (nx >= 0 && nx < H && ny >= 0 && ny < W && !visited[nx][ny][k - 1] && map[nx][ny] == 0) {
                        visited[nx][ny][k - 1] = true;
                        queue.offer(new int[] {nx, ny, k - 1, moveCount + 1});
                    }
                }
            }
        }
    }
}