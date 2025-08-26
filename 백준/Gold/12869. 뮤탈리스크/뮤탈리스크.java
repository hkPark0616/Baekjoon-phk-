import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, k;
    static int[] svc = new int[3];
    static int[][][] hp = new int[61][61][61];
    static int[][] damages = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            svc[i] = Integer.parseInt(st.nextToken());
        }

        bfs();
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {svc[0], svc[1], svc[2]});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];

            if(a <= 0 && b <= 0 && c <= 0) {
                System.out.println(hp[a][b][c]);
                return;
            }

            for(int[] damage: damages) {
                int na = Math.max(0, a - damage[0]);
                int nb = Math.max(0, b - damage[1]);
                int nc = Math.max(0, c - damage[2]);

                if(hp[na][nb][nc] == 0) {
                    hp[na][nb][nc] = hp[a][b][c] + 1;
                    q.offer(new int[] {na, nb, nc});
                }
            }
        }
    }
}