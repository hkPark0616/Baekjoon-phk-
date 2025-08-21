import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] adj;
    static boolean[][] road;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        adj = new int[N + 1][N + 1];
        road = new boolean[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
                if(i != j) road[i][j] = true;
            }
        }

        floyd();
    }

    static void floyd() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if (i == j || i == k || j == k) continue;

                    // 민호가 구한 최단시간보다 더 짧은 시간 경로가 존재
                    if(adj[i][j] > adj[i][k] + adj[k][j]) {
                        System.out.println(-1);
                        return;
                    }

                    // 어떤 k를 거쳐도 최단경로 유지
                    if(adj[i][j] == adj[i][k] + adj[k][j]) {
                        road[i][j] = false;
                    }
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                if(road[i][j]) answer += adj[i][j];
            }
        }

        System.out.println(answer);
    }
}