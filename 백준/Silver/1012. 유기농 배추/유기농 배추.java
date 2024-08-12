import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int M, N, K, cnt;
    public static int[][] baechu;
    public static boolean[][] visited;
    public static int delX[] = {1, 0, -1, 0};
    public static int delY[] = {0, 1, 0, -1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        
        for(int t = 0; t < T; t++) {
            
            
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();
            
            baechu = new int[M][N];
            visited = new boolean[M][N];
            
            for(int i = 0; i < K; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                baechu[a][b] = 1;
            }
            cnt = 0;
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < N ; j++) {
                    if(baechu[i][j] == 1 && !visited[i][j]) {
                        cnt++;
                        visited[i][j] = true;
                        bfs(i, j);
                        
                    }
                }
            }
            
            System.out.println(cnt);
        }
    }
    
    public static void bfs(int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {x, y});
        visited[x][y] = true;

        while(!que.isEmpty()) {
            int[] temp = que.poll();
            
            x = temp[0];
            y = temp[1];

            for(int i = 0; i < 4; i++) {
                int dx = x + delX[i];
                int dy = y + delY[i];
                if(dx >= 0 && dx < M && dy >= 0  && dy < N && baechu[dx][dy] == 1 && !visited[dx][dy]) {
                    que.offer(new int[] {dx, dy});
                    visited[dx][dy] = true;
                }
            }
            
        }
    }
}