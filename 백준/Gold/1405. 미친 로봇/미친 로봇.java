import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static double result;
    static double[] dir = new double[4];
    static boolean[][] visited = new boolean[30][30];
    static int[] dx = {0, 0, 1, -1}; // 동서남북
    static int[] dy = {1, -1, 0, 0}; 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        dir = new double[4];
        
        for(int i = 0; i < 4; i++) {
            dir[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }

        visited[15][15] = true;
        dfs(15, 15, 0, 1.0);

        System.out.println(result);
    }

    static void dfs(int x, int y, int depth, double p) {
        if(depth == N) {
            result += p;
            return;
        }

        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, p * dir[d]);
                visited[nx][ny] = false;
            }
        }
    }
}