import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K, S;
    static boolean[][] history;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        history = new boolean[N + 1][N + 1];
        
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            history[a][b] = true;
        }

        floyd();

        S = Integer.parseInt(br.readLine());
        for(int s = 0; s < S; s++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(history[a][b]) sb.append(-1).append("\n");
            else if(history[b][a]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        System.out.println(sb);
    }

    static void floyd() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                if(i == k) continue;
                for(int j = 1; j <= N ; j++) {
                    if(i == j || j == k) continue;
                    if(history[i][k] && history[k][j]) {
                        history[i][j] = true;
                    }
                }
            }
        }
    }
}