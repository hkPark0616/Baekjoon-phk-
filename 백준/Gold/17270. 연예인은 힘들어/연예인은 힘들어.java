import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int V, M, J, S;
    static int[][] dist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[V + 1][V + 1];
        for(int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], c);
            dist[b][a] = Math.min(dist[b][a], c);
        }

        st = new StringTokenizer(br.readLine());
        J = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        floyd();

        int bestSum = Integer.MAX_VALUE;
        for (int v = 1; v <= V; v++) {
            if (v == J || v == S) continue;
            
            int sDist = dist[S][v];
            int jDist = dist[J][v];
            
            int sum = sDist + jDist;
            bestSum = Math.min(bestSum, sum);
        }

        int bestJ = Integer.MAX_VALUE;
        int bestV = -1;
        for(int v = 1; v <= V; v++) {
            if (v == J || v == S) continue; // 1
            
            int sDist = dist[S][v];
            int jDist = dist[J][v];
            int sum = sDist + jDist;

            if(sum != bestSum) continue; // 2
            if(jDist > sDist) continue; // 3
            
            if(jDist < bestJ) { // 4
                bestJ = jDist;
                bestV = v;
            } else if(jDist == bestJ && v < bestV) { // 5
                bestV = v;
            }
        }

        System.out.println(bestV);
    }

    static void floyd() {
        for(int k = 1; k <= V; k++) {
            for(int i = 1; i <= V; i++) {
                for(int j = 1; j <= V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }      
                }
            }
        }
    }
}