import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] city;
    static int INF = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                if(i == j) continue;
                city[i][j] = INF;
            }
        }

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            city[A][B] = 1;
            city[B][A] = 1;
        }

        floyd();

        int minTotal = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;
        // i, j: 치킨집
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                int total = 0; // 각 건물에서 가장 가까운 치킨집까지 왕복하는 시간 총합
                // k: 각 건물
                for(int k = 1; k <= N; k++) {
                    int minDist = Math.min(city[k][i], city[k][j]);
                    total += minDist * 2; // 왕복
                }

                if(total < minTotal) {
                    a = i;
                    b = j;
                    minTotal = total;
                }
            }
        }

        System.out.println(a + " " + b + " " + minTotal);
    }

    static void floyd() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(city[i][j] > city[i][k] + city[k][j]) {
                        city[i][j] = city[i][k] + city[k][j];
                    }
                }
            }
        }
    }
}