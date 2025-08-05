import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static boolean[][] adj;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new boolean[N + 1][N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a > b
            adj[a][b] = true;
        }

        floyd();

        int half = (N + 1) / 2;
        int light; // 가벼운 구슬 개수
        int heavy; // 무거운 구슬 개수
        int cnt = 0;

        for(int i = 1; i <= N; i++) {
            light = 0; 
            heavy = 0;
            for(int j = 1; j <= N; j++) {
                if(adj[i][j]) heavy++; // i 무겁
                if(adj[j][i]) light++; // i 가볍
            }

            // 무거운 또는 가벼운 구슬 개수가 
            // 중간 개수 이상이면 중간 알 수 있음
            if(heavy >= half || light >= half) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }


    static void floyd() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(adj[i][k] && adj[k][j]) {
                        adj[i][j] = true;
                    }
                }
            }
        }
    }
}