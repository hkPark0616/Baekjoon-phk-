import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, T, M;
    static int[][] dist, pos;
    static boolean[] special;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        pos = new int[N][2];
        special = new boolean[N];
        
        // s가 1인 경우에는 특별한 도시, 0인 경우 일반 도시
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(s == 1) special[i] = true;
            pos[i][0] = x;
            pos[i][1] = y;
        }

        // i, j 쌍에 대해 이동 가능한 거리 계산, 이동 시간은 |r1 - r2| + |c1 - c2|
        // 특별한 도시라면, 텔레포트를 이용 가능. 텔레포트에 걸리는 시간은 T
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int d = Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);
                if(special[i] && special[j]) {
                    d = Math.min(d, T);
                }
                dist[i][j] = d;
            }
        }

        floyd();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dist[A][B]).append("\n");
        }

        System.out.println(sb);
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