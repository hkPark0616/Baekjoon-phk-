import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] adj;
    static int INF = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        adj = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(adj[i], INF);
            adj[i][i] = 0;
        }

        while(true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) break;

            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        floyd();

        int[] scores = new int[N + 1];
        int minScore = INF;
        for(int i = 1; i <= N; i++) {
            int score = 0;
            for(int j = 1; j <= N; j++) {
                if (adj[i][j] != INF) {
                    score = Math.max(score, adj[i][j]); // 가장 먼 거리거 점수
                }
            }
            scores[i] = score;
            minScore = Math.min(minScore, score);
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(minScore == scores[i]) {
                list.add(i);
            }
        }
        Collections.sort(list);

        System.out.println(minScore + " " + list.size());
        for(int n: list) System.out.print(n + " ");
        
    }

    static void floyd() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(adj[i][k] != INF && adj[k][j] != INF) {
                        adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);   
                    }
                }
            }
        }
    }
}