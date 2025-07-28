import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    final static int INF = 1000000000;
    static int N, K;
    static int min = Integer.MAX_VALUE;
    static int[][] dist;
    static boolean[] selected;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        selected = new boolean[N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                dist[i][j] = INF;
            }
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int time = Integer.parseInt(st.nextToken());
                dist[i][j] = time;
            }
        }

        floyd();
        selected[K] = true;
        find(K, 0, 1);

        System.out.println(min);
    }

    static void find(int cur, int time, int depth) {
        if(depth == N) {
            min = Math.min(min, time);
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(!selected[i]) {
                selected[i] = true;
                find(i, time + dist[cur][i], depth + 1);
                selected[i] = false;   
            }
        }
    }

    static void floyd() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                if(i == k) continue;
                for(int j = 0; j < N ; j++) {
                    if(i == j || j == k) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}