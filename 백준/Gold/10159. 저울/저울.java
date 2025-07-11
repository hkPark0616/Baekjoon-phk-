import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static boolean[][] adj;
    static List<Integer> results = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new boolean[N + 1][N + 1];
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a][b] = true;
        }

        floyd();
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

        for(int i = 1; i <= N; i++) {
            int cnt = 0;
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                
                if(!adj[i][j] && !adj[j][i]) {
                    cnt++;
                }
            }

            results.add(cnt);
        }

        for(int n: results) System.out.println(n);
    }
}