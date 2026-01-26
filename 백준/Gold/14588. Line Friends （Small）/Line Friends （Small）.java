import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, Q;
    static int[] L, R;
    static int[][] dist;
    static int MAX = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        L = new int[N];
        R = new int[N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            L[i] = Integer.parseInt(st.nextToken());
            R[i] = Integer.parseInt(st.nextToken());
        }

        connect();

        floyd();
        
        Q = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dist[A][B] == MAX ? -1 : dist[A][B]).append("\n");
        }

        System.out.println(sb);
    }

    static void connect() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                if(!(R[i] < L[j] || R[j] < L[i])) {
                    dist[i][j] = 1;
                }
            }
        }
    }

    static void floyd() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}