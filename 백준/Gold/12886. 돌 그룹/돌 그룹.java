import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int A, B, C, sum;
    static boolean[][] visited = new boolean[1501][1501];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        sum = A + B + C;
        
        System.out.println(bfs(A, B, C));
    }

    static int bfs(int a, int b, int c) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {a, b});
        visited[a][b] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int z = sum - x - y;

            if(x == y && y == z) return 1;

            int[] stones = {x, y, z};

            for(int i = 0; i < 3; i++) {
                for(int j = i + 1; j < 3; j++) {
                    int X = stones[i];
                    int Y = stones[j];

                    if(X == Y) continue;

                    if(X < Y) {
                        int nx = X * 2;
                        int ny = Y - X;
                        int nz = sum - nx - ny;

                        int na = Math.min(nx, ny);
                        int nb = Math.max(nx, ny);

                        if(!visited[na][nb]) {
                            visited[na][nb] = true;
                            q.offer(new int[] {na, nb});
                        }
                    } else {
                        int nx = Y * 2;
                        int ny = X - Y;
                        int nz = sum - nx - ny;

                        int na = Math.min(nx, ny);
                        int nb = Math.max(nx, ny);

                        if(!visited[na][nb]) {
                            visited[na][nb] = true;
                            q.offer(new int[] {na, nb});
                        }  
                    }
                }
            }
        }
        
        return 0;
    }
}