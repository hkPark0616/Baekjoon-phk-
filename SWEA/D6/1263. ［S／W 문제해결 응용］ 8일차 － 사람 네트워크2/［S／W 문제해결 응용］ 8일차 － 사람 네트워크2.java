import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, answer;
    static int INF = 1000000000;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            graph = new int[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if(graph[i][j] == 0) {
                        if(i == j) graph[i][j] = 0;
                        else graph[i][j] = INF;
                    }
                }
            }
            answer = Integer.MAX_VALUE;
            floyd(graph);
            System.out.println("#" + t + " " + answer);
        }
    }

    static void floyd(int[][] graph){
        // 경유지
        for(int k = 0; k < N; k++){
            // 출발지
            for(int i = 0; i < N; i++){
                if(i == k) continue;
                // 도착지
                for(int j = 0; j < N; j++){
                    if(i == j || j == k) continue;
                    // 경유지를 거쳐가는 것이 더 빠르다면 갱신
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        // 최소 갱신
        for(int i = 0; i < N; i++){
            int sum = 0;
            for(int j = 0; j < N; j++){
                if(graph[i][j] != INF){
                    sum += graph[i][j];
                }
            }
            answer = Math.min(answer, sum);
        }
    }
}
