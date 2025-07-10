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

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 키가 a보다 b가 크다 -> adj[a][b];
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a][b] = true;
        }


        System.out.println(floyd());
    }

    static int floyd() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(adj[i][k] && adj[k][j]) {
                        adj[i][j] = true;
                    }
                }
            }
        }

        int result = 0;
        for(int i = 1; i <= N; i++) {
            int known = 0;
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;

                // 해당 학생보다 키 작은애 또는 키 큰애를 모두 알면 순서를 알 수 있음
                if(adj[i][j] || adj[j][i]) {
                    known++;
                }
            }

            if(known == N - 1) result++;
        }
        
        return result;
    }
}