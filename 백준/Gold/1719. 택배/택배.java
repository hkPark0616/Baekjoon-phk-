import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static final int INF = 1000000000;
    static int[][] graph, route;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        route = new int[N + 1][N + 1];
        
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }
        
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from][to] = Math.min(graph[from][to], time);
            graph[to][from] = Math.min(graph[to][from], time);

            // 다음에 이동해야 할 경로(집하장)를 저장하고 출력하기 위함
            route[from][to] = to;
            route[to][from] = from;
        }

        floyd();
        print();

        System.out.println(sb.toString());
    }

    static void floyd() {
        // k: 경유
        for(int k = 1; k <= N; k++) {
            // i: 출발
            for(int i = 1; i <= N; i++) {
                if(i == k) continue;
                // j: 도착
                for(int j = 1; j <= N; j++) {
                    if(i == j || j == k) continue;
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        route[i][j] = route[i][k]; // i -> j로 가기 위한 첫 경로는 i -> k 임
                    }
                }
            }
        }
    }

    static void print() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) sb.append("-").append(" ");
                else sb.append(route[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }
}