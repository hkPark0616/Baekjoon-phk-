import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R, items[], graph[][], answer;
    static int INF = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        items = new int[N + 1];
        graph = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = c;
        }

        answer = 0;
        floyd();

        System.out.println(answer);
    }

    static void floyd() {
        for(int k = 1; k <= N; k++){ // 경유
            for(int i = 1; i <= N; i++){ // 출발
                for(int j = 1; j <= N; j++){ // 도착
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            int max = 0;
            for(int j = 1; j <= N; j++){
                if(graph[i][j] <= M){ // 수색범 M 보다 작거나 같아야 아이템을 얻을 수 있음
                    max += items[j];
                }
            }
            answer = Math.max(answer, max);
        }
    }
}